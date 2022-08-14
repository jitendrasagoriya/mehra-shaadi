package com.jitendra.mehra.shaadi.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jitendra.mehra.shaadi.constants.AppConstants;
import com.jitendra.mehra.shaadi.dao.CandidateDao;
import com.jitendra.mehra.shaadi.dto.MehraAuthentication;
import com.jitendra.mehra.shaadi.entity.Candidate;
import com.jitendra.mehra.shaadi.search.CandidateSpecification;
import com.jitendra.mehra.shaadi.search.SearchCriteria;
import com.jitendra.mehra.shaadi.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {
	
	private static final Logger logger =   LogManager.getLogger(CandidateServiceImpl.class);

	@Autowired
	private CandidateDao candidateDao;

	@Override
	public Candidate register(MehraAuthentication authentication) {
		Candidate candidate = Candidate.builder().id(authentication.getUserId()).email(authentication.getUserName())
				.contactInfo(authentication.getMobileNumber()).name(authentication.getName())
				.gender(authentication.getGender()).build();
		return candidateDao.save(candidate);
	}

	@Override
	public Candidate getCandidate(String id) {
		return candidateDao.getById(id).orElse(null);
	}

	@Override
	public Page<Candidate> getAllFemales(Pageable pageable) {
		return candidateDao.getCandidateByGender(AppConstants.FEMALE, pageable);
	}

	@Override
	public Page<Candidate> getAllMales(Pageable pageable) {
		return candidateDao.getCandidateByGender(AppConstants.MALE, pageable);
	}

	@Override
	public Page<Candidate> getCandidateBySpec(Specification<Candidate> specification, Pageable pageable) {
		return candidateDao.getCandidateBySpec(specification, pageable);
	}

	@Override
	public Page<Candidate> getCandidateByPreference(String gender, Pageable pageable) {
		if (StringUtils.equalsAnyIgnoreCase(gender, AppConstants.MALE))
			gender = AppConstants.FEMALE;
		else
			gender = AppConstants.MALE;
		Specification<Candidate> specification = new CandidateSpecification(new SearchCriteria("gender", ":", gender));

		return getCandidateBySpec(specification, pageable);
	}

	@Override
	public Candidate update(Candidate candidate) {
		return candidateDao.save(candidate);
	}

	@Override
	public Boolean isProfileCompleted(Candidate candidate) {
		return getNullFields(candidate).length == 0;
	}

	public static String[] getNullFields(Object obj) {
		List<String> al = new ArrayList<String>();
		if (obj != null) { // Check for null input.
			Class<?> cls = obj.getClass();
			Field[] fields = cls.getFields();
			for (Field f : fields) {
				try {
					if (f.get(obj) == null) { // Check for null value.
						al.add(f.getName()); // Add the field name.
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		String[] ret = new String[al.size()]; // Create a String[] to return.
		return al.toArray(ret); // return as an Array.
	}

	@Override
	public Boolean markProfileCompleted(String id) {
		if(logger.isDebugEnabled())
			logger.debug("markProfileCompleted : ID : {}"+id );
		
		try {
			Optional<Candidate> optional = candidateDao.getById(id);
			if (optional.isPresent()) {
				Candidate candidate = optional.get();
				candidate.setIsProfileCompleted(isProfileCompleted(candidate));
				candidateDao.save(candidate);
			}
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
}
