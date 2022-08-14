package com.jitendra.mehra.shaadi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jitendra.mehra.shaadi.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, String>,JpaSpecificationExecutor<Candidate> {}
