package com.jitendra.mehra.shaadi.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.jitendra.mehra.shaadi.entity.Candidate;

public class CandidateSpecification implements Specification<Candidate>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	
	private SearchCriteria criteria;
	
	

	public CandidateSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}



	@Override
	public Predicate toPredicate(Root<Candidate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		 if (criteria.getOperation().equalsIgnoreCase(">")) {
	            return builder.greaterThanOrEqualTo(
	              root.<String> get(criteria.getKey()), criteria.getValue().toString());
	        } 
	        else if (criteria.getOperation().equalsIgnoreCase("<")) {
	            return builder.lessThanOrEqualTo(
	              root.<String> get(criteria.getKey()), criteria.getValue().toString());
	        } 
	        else if (criteria.getOperation().equalsIgnoreCase(":")) {	        	
	        	return builder.equal(root.get(criteria.getKey()), criteria.getValue());
	           
	        } else if(criteria.getOperation().equalsIgnoreCase("%%")) {
	        	 if (root.get(criteria.getKey()).getJavaType() == String.class) {
		                return builder.like(
		                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
	        	 }
	        }
	        return null;
	}

}
