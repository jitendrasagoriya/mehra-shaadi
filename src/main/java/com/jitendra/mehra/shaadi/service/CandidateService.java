package com.jitendra.mehra.shaadi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jitendra.mehra.shaadi.dto.MehraAuthentication;
import com.jitendra.mehra.shaadi.entity.Candidate;


@Service
public interface CandidateService {
		
	public Candidate register(MehraAuthentication authentication);
	
	public Candidate getCandidate(String id);

	public Page<Candidate> getAllFemales(Pageable pageable);
	
	public Page<Candidate> getAllMales(Pageable pageable);
	
	public Page<Candidate> getCandidateBySpec(Specification<Candidate> specification, Pageable pageable);
	
	public Page<Candidate> getCandidateByPreference(String gender, Pageable pageable);
	
	public Candidate update(Candidate candidate);
	
	public Boolean isProfileCompleted(Candidate candidate);
	
	public Boolean markProfileCompleted(String id);
}
