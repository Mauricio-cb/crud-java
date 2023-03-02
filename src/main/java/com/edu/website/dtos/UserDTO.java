package com.edu.website.dtos;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(UUID id, String username, String email) {
}
