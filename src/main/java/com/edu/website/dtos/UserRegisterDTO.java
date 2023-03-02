package com.edu.website.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO (@NotBlank  String username, @NotBlank  String password, @NotBlank @Email String email){
}
