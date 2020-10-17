
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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( name = "user" )
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String username;

	@NotNull
	private String password;

	private String address;

	private String state;

	private String country;

	@NotNull
	private String email;

	private String permanentAccountNumber;

	private String contactNumber;


	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;

	private String accountType;

	private boolean isActive;
	private boolean isCreentialsExpired;
	private boolean isAccountExpired;
	private boolean isAccountLocked;



	// Just to make the account status non editable
	@PrePersist
	private void prePersist() {
		this.createdOn = OffsetDateTime.now();
		accountStatus();
	}

	@PreUpdate
	private void preUpdate() {
		this.lastUpdatedOn = OffsetDateTime.now();
		accountStatus();
	}

	private void accountStatus() {
		this.isActive = true;
		this.isCreentialsExpired = false;
		this.isAccountLocked = false;
		this.isAccountExpired = false;
	}


	// Audit data
	private OffsetDateTime createdOn;
	private OffsetDateTime lastUpdatedOn;


}

