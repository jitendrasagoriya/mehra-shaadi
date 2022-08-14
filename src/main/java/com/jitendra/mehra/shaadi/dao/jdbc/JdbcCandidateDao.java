package com.jitendra.mehra.shaadi.dao.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jitendra.mehra.shaadi.dao.CandidateDao;
import com.jitendra.mehra.shaadi.entity.Candidate;
import com.jitendra.mehra.shaadi.repository.CandidateRepository;
import com.jitendra.mehra.shaadi.search.CandidateSpecification;
import com.jitendra.mehra.shaadi.search.SearchCriteria;

@Service
public class JdbcCandidateDao implements CandidateDao {
	
	@Autowired
	private CandidateRepository repository;
	

	@Override
	public Candidate save(Candidate candidate) {		 
		return repository.saveAndFlush(candidate);
	}

	@Override
	public Optional<Candidate> getById(String id) { 
		return repository.findById(id);
	}

	@Override
	public Page<Candidate> getCandidateByGender(String gender, Pageable pageable) {
		CandidateSpecification specification = new CandidateSpecification(new SearchCriteria("gender", ":", gender));
		return getCandidateBySpec(specification, pageable);
	}

	@Override
	public Page<Candidate> getCandidateBySpec(Specification<Candidate> specification, Pageable pageable) {
		return repository.findAll(specification,pageable);
	}

	@Override
	public List<Candidate> getCandidateBySpec(Specification<Candidate> specification) {
		return repository.findAll(specification);
	}

	@Override
	public Candidate update(Candidate candidate) {
		return save(candidate);
	}

	@Override
	public Boolean isProfileCompleted(String id) {
		Optional<Candidate> candidate = getById(id);
		if(candidate.isPresent())
			return getById(id).get().getIsProfileCompleted();
		else
			return Boolean.FALSE;
	}

}
