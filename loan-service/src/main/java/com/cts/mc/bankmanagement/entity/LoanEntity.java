
package com.cts.mc.bankmanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( name = "loan_data" )
public class LoanEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
	
	@NotNull
	@NotEmpty
    private String username;
	
	@NotNull
	@NotEmpty
	private String loanType;
	
	@NotNull
	private Double loanAmount;
	
	@NotNull
	private LocalDate loanDate;
	
	
	private Double rateOfInterest;
	
	private Integer loanDuration;
	
    
    // Audit data
    private OffsetDateTime createdOn;
    private OffsetDateTime lastUpdatedOn;
    
    
    // Just to make the account status non editable
    @PrePersist
    private void prePersist() {
    	this.createdOn = OffsetDateTime.now();
    }
    
    @PreUpdate
    private void preUpdate() {
    	this.lastUpdatedOn = OffsetDateTime.now();
    }
    
}

