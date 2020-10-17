
package com.cts.mc.bankmanagement.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.cts.mc.bankmanagement.validator.ValidateUser;
import com.cts.mc.bankmanagement.validator.groups.Create;
import com.cts.mc.bankmanagement.validator.groups.Update;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chiranjitbhattacharya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidateUser(groups = { Create.class } )
@ValidateUser(groups = { Update.class } )
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Schema( description = "Unique user ID(Auto generrated and non editable)", example = "109982" )
	private Long id;

	@NotNull( groups = { Create.class, Update.class } )
	@NotEmpty( groups = { Create.class, Update.class } )
	@Schema( description = "Name of the user", example = "John", required = true  )
	private String name;

	@NotNull( groups = { Create.class, Update.class } )
	@NotEmpty( groups = { Create.class, Update.class } )
	@Schema( description = "Username of the user(Non editable once created)", example = "john", required = true  )
	private String username;

	@NotNull( groups = { Create.class, Update.class } )
	@NotEmpty( groups = { Create.class, Update.class } )
	@Schema( description = "Password for the account", example = "secret", required = true  )
	private String password;


	@Schema( description = "Address of the user", example = "New Town" )
	private String address;


	@Schema( description = "State", example = "WB" )
	private String state;


	@Schema( description = "Country", example = "India" )
	private String country;

	@NotNull( groups = { Create.class, Update.class } )
	@NotEmpty( groups = { Create.class, Update.class } )
	@Schema( description = "Email Id of the user", example = "john@company.com", required = true  )
	private String email;



	@Schema( description = "Permanent Account Number(PAN) of the user", example = "CBYPB2627D" )
	private String permanentAccountNumber;



	@Schema( description = "Contact Number of the user", example = "7477448183" )
	private String contactNumber;


	@JsonFormat(pattern = "dd-MM-yyyy")
	@Schema( description = "Date of birth of the user(Format: DD-MM-YYYY)", example = "01-01-1990" )
	private LocalDate dateOfBirth;

	@NotNull( groups = { Create.class, Update.class } )
	@NotEmpty( groups = { Create.class, Update.class } )
	@Schema( description = "Account type", example = "Savings", required = true )
	private String accountType;


	@Schema( description = "Flag indicating whether the user account is activated(Non Editable)", example = "true" )
	private boolean isActive;

	@Schema( description = "Flag indicating whether the creadentials are expired(Non Editable)", example = "false" )
	private boolean isCreentialsExpired;

	@Schema( description = "Flag indicating whether the user account is expired(Non Editable)", example = "false" )
	private boolean isAccountExpired;

	@Schema( description = "Flag indicating whether the user account is locked(Non Editable)", example = "false" )
	private boolean isAccountLocked;


	// Audit data
	private OffsetDateTime createdOn;
	private OffsetDateTime lastUpdatedOn;


}

