package com.jitendra.mehra.shaadi.endpoint;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.mehra.shaadi.constants.AppConstants;
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
@RequestMapping(path = "/search/", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(value = "Candidate Search ", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-LOG-HEADER", value = "Add Your Access Token", paramType = "header", dataType = "string", required = true, dataTypeClass = String.class) })
public class CandidateSearchEndpoint {
	
	private static final Logger logger =   LogManager.getLogger(CandidateSearchEndpoint.class);

	@Autowired
	private CandidateService service;

	@ApiOperation(value = "Get Candidate By User Gender", produces = MediaType.APPLICATION_JSON_VALUE, nickname = "getCandidateByPreference", authorizations = {
			@Authorization(HttpHeaders.AUTHORIZATION) })
	@GetMapping(path = "list/byUser/")
	public ResponseEntity<?> getCandidateByPreference(@ApiIgnore @RequestAttribute(name = "userId") String userId,
			@PageableDefault(page = AppConstants.DEFAULT_PAGE_NUMBER, size = AppConstants.DEFAULT_PAGE_SIZE) Pageable pageable) {
		
		logger.info("User Id :"+ userId + " Page :" + pageable.toString());
		try {
			Candidate candidate = service.getCandidate(userId);
			if (candidate == null)
				return new ResponseEntity<>("Unauthorized Request", HttpStatus.UNAUTHORIZED);

			return new ResponseEntity<>(service.getCandidateByPreference(candidate.getGender(), pageable),
					HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Get Candidate By Gender", produces = MediaType.APPLICATION_JSON_VALUE, nickname = "getCandidateByGender:Female", authorizations = {
			@Authorization(HttpHeaders.AUTHORIZATION) })
	@GetMapping(path = "female/")
	public ResponseEntity<?> getByGenderValueFemale(@ApiIgnore @RequestAttribute(name = "userId") String userId,
			@PageableDefault(page = AppConstants.DEFAULT_PAGE_NUMBER, size = AppConstants.DEFAULT_PAGE_SIZE) Pageable pageable) {
		logger.info("User Id :"+ userId + " Page :" + pageable.toString());
		try {
			Candidate candidate = service.getCandidate(userId);
			if (candidate == null)
				return new ResponseEntity<>("Unauthorized Request", HttpStatus.UNAUTHORIZED);

			return new ResponseEntity<>(service.getAllFemales(pageable), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Get Candidate By Gender", produces = MediaType.APPLICATION_JSON_VALUE, nickname = "getCandidateByGender:Male", authorizations = {
			@Authorization(HttpHeaders.AUTHORIZATION) })
	@GetMapping(path = "male/")
	public ResponseEntity<?> getByGenderValueMale(@ApiIgnore @RequestAttribute(name = "userId") String userId,
			@PageableDefault(page = AppConstants.DEFAULT_PAGE_NUMBER, size = AppConstants.DEFAULT_PAGE_SIZE) Pageable pageable) {
		logger.info("User Id :"+ userId + " Page :" + pageable.toString());
		try {
			Candidate candidate = service.getCandidate(userId);
			if (candidate == null)
				return new ResponseEntity<>("Unauthorized Request", HttpStatus.UNAUTHORIZED);

			return new ResponseEntity<>(service.getAllMales(pageable), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
