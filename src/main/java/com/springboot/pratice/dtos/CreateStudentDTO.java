package com.springboot.pratice.dtos;

import jakarta.validation.constraints.*;

public record CreateStudentDTO(

        @NotNull(message = "Roll number is required")
        @Positive(message = "Roll number must be greater than zero")
        @Min(value = 100, message = "Roll number must be at least 100")
        @Max(value = 999999, message = "Roll number cannot exceed 6 digits")
        Long rollNumber,

        @NotBlank(message ="First name is required")
        @Size(min = 3,max = 50, message = "First name must be between 3 and 50 characters")
        String firstName,

        @NotBlank(message = "Second name is required")
        @Size(min = 3,max = 50,message = "Second name must be between 3 and 50 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Please give the valid email")
        String studentEmail,

        @NotBlank(message = "Contact is required")
        @Pattern(regexp = "^[0-9]+$", message = "Please give the valid mobile number")
        String studentContact,

        String studentAddress
) {
}
