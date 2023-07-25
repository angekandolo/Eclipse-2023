package com.perscholas.capstone.repository;
import com.perscholas.capstone.entity.FormSubmission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Long> {
	

}
