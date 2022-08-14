package com.jitendra.mehra.shaadi.endpoint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.mehra.shaadi.entity.Candidate;
import com.jitendra.mehra.shaadi.service.CandidateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@RestController
@RequestMapping(path = "/candidate/profile/", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api( value = "Candidate Profile ", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-LOG-HEADER", value = "Add Your Access Token", paramType = "header", dataType = "string", required = true, dataTypeClass = String.class) })
public class ProfileEndpoint {
	
	private static final Logger logger =   LogManager.getLogger(ProfileEndpoint.class);
	
	ExecutorService executorService = Executors.newFixedThreadPool(10);  
	
	@Autowired
	private CandidateService service;


	@ApiOperation( value = "Get Candidate By ID", produces = MediaType.APPLICATION_JSON_VALUE, nickname = "getCandidateByID", authorizations = {
			@Authorization(HttpHeaders.AUTHORIZATION) })
	@GetMapping(path = {""})
	public ResponseEntity<?> getById(@ApiIgnore @RequestAttribute(name = "userId") String userId) {
		try {
			return new ResponseEntity<>(service.getCandidate(userId), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@ApiOperation( value = "Update Candidate Profile", produces = MediaType.APPLICATION_JSON_VALUE, nickname = "updateCandidate", authorizations = {
			@Authorization(HttpHeaders.AUTHORIZATION) })
	@PutMapping(path = {""})
	public ResponseEntity<?> updateCandidate(@ApiIgnore @RequestAttribute(name = "userId") String userId, @RequestBody Candidate candidate) {
		try {
			Candidate dbCandidate = service.getCandidate(userId);
			if(dbCandidate == null)
				return new ResponseEntity<>("Unauthorized Request",HttpStatus.UNAUTHORIZED);
			
			candidate.setId(dbCandidate.getId());
			candidate.setEmail(dbCandidate.getEmail());
			candidate.setContactInfo(dbCandidate.getContactInfo());
			candidate.setName(dbCandidate.getName());
			candidate.setGender(dbCandidate.getGender());
			
			candidate = service.update(candidate);
			candidate.setIsProfileCompleted(service.isProfileCompleted(candidate));
			
			executorService.execute( () -> {
				logger.info("Thread Running....");
				service.markProfileCompleted(userId);
			});
			 
			return new ResponseEntity<>(candidate, HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
