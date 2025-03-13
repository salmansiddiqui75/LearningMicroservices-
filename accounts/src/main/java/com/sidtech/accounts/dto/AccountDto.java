package com.sidtech.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountDto
{
    @Schema(
            description = "Account Number of Eazy Bank account", example = "3454433243"
    )
    @NotNull(message = "Account number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digit")
    private Long accountNumber;

    @Schema(
            description = "Account type of Eazy Bank account", example = "Savings"
    )
    @NotNull(message = "Account type can't be null or empty")
    private String accountType;

    @Schema(
            description = "Eazy Bank branch address", example = "123 NewYork"
    )
    @NotNull(message = "Branch address can't be null or empty")
    private String branchAddress;
}
