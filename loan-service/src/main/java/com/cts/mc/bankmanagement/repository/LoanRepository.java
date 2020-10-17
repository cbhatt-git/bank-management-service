package com.cts.mc.bankmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.mc.bankmanagement.entity.LoanEntity;

/**
 * Repository for loan data
 * @author chiranjitbhattacharya
 *
 */
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
	
	/**
	 * Query to find all load for a user.
	 * @param username String - User name 
	 * @return
	 */
	List<LoanEntity> findByUsername(String username);

}
