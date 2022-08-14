package com.jitendra.mehra.shaadi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jitendra.mehra.shaadi.entity.Candidate;

@Service
public interface CandidateDao {
	
	public Candidate save(Candidate candidate);
	
	public Optional<Candidate> getById(String id);
	
	public Page<Candidate> getCandidateByGender(String gender, Pageable pageable);
	
	public Page<Candidate> getCandidateBySpec(Specification<Candidate> specification, Pageable pageable);
	
	public List<Candidate> getCandidateBySpec(Specification<Candidate> specification);
	
	public Candidate update(Candidate candidate);
	
	public Boolean isProfileCompleted(String id);
	
	

}
