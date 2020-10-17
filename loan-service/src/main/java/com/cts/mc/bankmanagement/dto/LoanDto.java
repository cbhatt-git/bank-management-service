
package com.cts.mc.bankmanagement.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class LoanDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Schema( description = "Unique loan ID(Auto generated)", example = "1000021")
	private Long loanId;

	@Schema( description = "Username(Auto detected, non editable)", example = "someuser")
	private String username;

	@NotNull
	@NotEmpty
	@Schema( description = "Loan type", example = "Car")
	private String loanType;

	@NotNull
	@Schema( description = "Amount of the loan", example = "500000")
	private Double loanAmount;

	@NotNull
	@Schema( description = "Loan date with format as YYYY-MM-DD", example = "2020-01-01")
	private LocalDate loanDate;


	@Schema( description = "Rate of interest", example = "14.5")
	private Double rateOfInterest;

	@Schema( description = "Duration of the loan (in month)", example = "24")
	private Integer loanDuration;

}

