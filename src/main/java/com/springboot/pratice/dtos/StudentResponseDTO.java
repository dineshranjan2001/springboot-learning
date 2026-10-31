package com.springboot.pratice.dtos;

import lombok.Builder;

import java.time.Instant;

@Builder
public record StudentResponseDTO(
        String id,
        Long rollNumber,
        String firstName,
        String lastName,
        String studentEmail,
        String studentContact,
        String studentAddress,
        Boolean isDeleted,
        Instant createdAt,
        Instant updatedAt
) {
}
