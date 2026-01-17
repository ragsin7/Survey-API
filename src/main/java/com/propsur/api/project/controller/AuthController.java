package com.propsur.api.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propsur.api.project.bean.TmUsersBean;
import com.propsur.api.project.dao.JwtRequest;
import com.propsur.api.project.dao.JwtResponse;
import com.propsur.api.project.entity.TmUsers;
import com.propsur.api.project.security.JwtHelper;
import com.propsur.api.project.service.UserServiceApi;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	   @Autowired
	    private UserDetailsService userDetailsService;
	   
	   @Autowired
	    private UserServiceApi userService;

	    @Autowired
	    private AuthenticationManager manager;


	    @Autowired
	    private JwtHelper helper;

	    //private Logger logger = LoggerFactory.getLogger(AuthController.class);

	    /*{
	    "username":"raghav",
	    "password":"raghav"
	    }
	    */
	    
	    @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

	        this.doAuthenticate(request.getUsername(), request.getPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
	        String token = this.helper.generateToken(userDetails);
	        
	        TmUsers tmUsers=userService.getUserByUserLoginName(request.getUsername());
	        
	        request.setUserId(tmUsers.getUserId());
	        //userService.updateLoginDetails(request);
	        
	        JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .username(userDetails.getUsername())
	                .tmUsers(tmUsers).build();
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    
	    //For Agent
	    @PostMapping("/login-agent")
	    public ResponseEntity<JwtResponse> loginAgent(@RequestBody JwtRequest request) {

	        this.doAuthenticate(request.getUsername(), request.getPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
	        String token = this.helper.generateToken(userDetails);
	        
	        TmUsersBean tmUsers=userService.getUserByUserLoginNameBean(request.getUsername());
	        
	        request.setUserId(tmUsers.getUserId());
	        //userService.updateLoginDetails(request);
	        
	        JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .username(userDetails.getUsername())
	                .tmUsersBean(tmUsers).build();
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    private void doAuthenticate(String username, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }
	    
	    @PostMapping("/logout")
	    public ResponseEntity<String> logout(@RequestBody JwtRequest request) {
	    	 //userService.updateLoginDetails(request);
	    	 return new ResponseEntity<>("Logout Successfully", HttpStatus.OK);
	    }


	    @ExceptionHandler(BadCredentialsException.class)
	    public ResponseEntity<Map<String, String>> handleBadCredentialsException() {
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Credentials Invalid !!");
	        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); // or 401
	    }
	   
	
}
