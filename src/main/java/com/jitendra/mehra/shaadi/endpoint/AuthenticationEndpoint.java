package com.jitendra.mehra.shaadi.endpoint;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.js.autenticationclient.auth.CommonAuthentication;
import org.js.autenticationclient.auth.impl.CommonAuthenticationImpl;
import org.js.autenticationclient.bean.Authentication;
import org.js.autenticationclient.registration.Registration;
import org.js.autenticationclient.registration.impl.RegistrationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.mehra.shaadi.constants.AppConstants;
import com.jitendra.mehra.shaadi.dto.LoggedInUser;
import com.jitendra.mehra.shaadi.dto.MehraAuthentication;
import com.jitendra.mehra.shaadi.entity.Candidate;
import com.jitendra.mehra.shaadi.service.CandidateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@RestController
@RequestMapping(path = "/",produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(value = "Register New Candidate ",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationEndpoint {
	
	private static final Logger logger =   LogManager.getLogger(AuthenticationEndpoint.class);
	
	Registration registration = new RegistrationImpl();

    CommonAuthentication commonAuthentication = new CommonAuthenticationImpl();
    
    @Autowired
    private CandidateService candidateService;
    
    
    @PostMapping(path = "register",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> registerYourself(@RequestBody MehraAuthentication authentication) {
        try {
        	logger.info("MehraAuthentication :" + authentication);	
            Authentication authentication1 =  registration.registerYouMember(authentication);
            MehraAuthentication mehraAuthentication = new MehraAuthentication(authentication1);
            mehraAuthentication.setMobileNumber(authentication.getMobileNumber());
            mehraAuthentication.setName(authentication.getName());
            mehraAuthentication.setGender(authentication.getGender());
            mehraAuthentication.afterPropertiesSet();
            
            Candidate candidate =   candidateService.register(mehraAuthentication);
            return new ResponseEntity<>(LoggedInUser.build(candidate),HttpStatus.OK);
        } catch (Exception exception) {
        	logger.error(exception);
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "login",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> login(@RequestParam(name = "username" ,defaultValue = "jitendra.sagoriya.jio@gmail.com") String userName, @RequestParam(name = "password",defaultValue = "J1tendra") String password) {
       
    	 
    	try{
            String _token =  commonAuthentication.getToken(userName,password);
            if(StringUtils.isBlank(_token)) {
                return new ResponseEntity<>("username and password is incorrect;",HttpStatus.UNAUTHORIZED);
            }
            Authentication authentication =  commonAuthentication.authenticate(_token);     
            if(authentication!=null && !authentication.getIsActive()) {
            	return new ResponseEntity<>("User is not active.",HttpStatus.BAD_REQUEST);
            }
            Candidate candidate = candidateService.getCandidate(authentication.getUserId());
            
            LoggedInUser loggedInUser = LoggedInUser.build(candidate);
            loggedInUser.setToken(authentication.getToken());
            
            return new ResponseEntity<LoggedInUser>(loggedInUser,HttpStatus.OK);
        }catch (Exception exception) {
        	exception.printStackTrace();
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Email Verification", produces = MediaType.APPLICATION_JSON_VALUE, nickname = "verifyEmail", authorizations = {
			@Authorization(HttpHeaders.AUTHORIZATION) })
	@GetMapping(path = "email/verify/")
	public ResponseEntity<?> verifyEmail(@ApiIgnore @RequestAttribute(name = "userId") String userId) {
		
		try { 

			return new ResponseEntity<>("verifyed",
					HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @ApiOperation(value = "Forgot Password", produces = MediaType.APPLICATION_JSON_VALUE, nickname = "forgot-password" )
    @PostMapping(path = "forgotPassword/")
	public ResponseEntity<?> forgotPassword(@RequestAttribute(name = "email") String email) {		
		try { 
			 
			return new ResponseEntity<>("verifyed",
					HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    
    

}
