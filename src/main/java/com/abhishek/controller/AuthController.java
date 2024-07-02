package com.abhishek.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.config.JwtProvider;
import com.abhishek.entity.Cart;
import com.abhishek.entity.USER_ROLE;
import com.abhishek.entity.User;
import com.abhishek.repository.CartRepository;
import com.abhishek.repository.UserRepository;
import com.abhishek.request.LoginRequest;
import com.abhishek.response.AuthResponse;
import com.abhishek.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private CartRepository cartRepository;
	
	//Checking if there is any email exist then it will show error other with it will make an account
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
		User isEmailExist=userRepository.findByEmail(user.getEmail());
		if(isEmailExist!=null) {
			throw new Exception("User is already used in another account");
		}
		User createUser=new User();
		createUser.setEmail(user.getEmail());
		createUser.setFullName(user.getFullName());
		createUser.setRole(user.getRole());
		createUser.setPassword(passwordEncoder.encode(user.getPassword()));
		User saveUser=userRepository.save(createUser);
		
		Cart cart=new Cart();
		cart.setCustomer(saveUser);
		cartRepository.save(cart);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//Generating JWT token
		String jwt=jwtProvider.generateToken(authentication);
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMassage("Registrating Success...");
		authResponse.setRole(saveUser.getRole());
		
		
		return new ResponseEntity<>(authResponse,HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	//Login process
	public ResponseEntity<AuthResponse> sighIn(@RequestBody LoginRequest req){
		String username=req.getEmail();
		String password=req.getPassword();
		
		Authentication authentication=authenticate(username,password);
		
		Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
		String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
		//generating token
		String jwt=jwtProvider.generateToken(authentication);
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMassage("Registrating Success...");
		//getting the role
		
		authResponse.setRole(USER_ROLE.valueOf(role));
		
		
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
		
	
	}

	private Authentication authenticate(String username, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid Username...");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new  BadCredentialsException("Invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	}
	
}
