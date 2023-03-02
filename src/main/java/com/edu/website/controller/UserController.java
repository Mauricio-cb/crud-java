package com.edu.website.controller;
import com.edu.website.dtos.UserDTO;
import com.edu.website.dtos.UserRegisterDTO;
import com.edu.website.models.UserModel;
import com.edu.website.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") UUID id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUser(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> saveUser (@RequestBody @Valid UserRegisterDTO user){
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser (@RequestBody UserModel user){
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteUser (@PathVariable UUID id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}
