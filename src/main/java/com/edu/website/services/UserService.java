package com.edu.website.services;

import com.edu.website.dtos.UserDTO;
import com.edu.website.dtos.UserRegisterDTO;
import com.edu.website.models.UserModel;
import com.edu.website.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

@Service
@Transactional
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUser(UUID id) {
        UserModel model = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find user"));

        return UserDTO.builder()
                .id(model.getId())
                .username(model.getUsername())
                .email(model.getEmail())
                .build();
    }

    public List<UserDTO> getAllUsers() {
        List<UserModel> users = userRepository.findAll();
        List<UserDTO> allUsers = new ArrayList<>();

        users.forEach((user) -> allUsers.add(UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build()));
        return allUsers;
    }


    public UUID saveUser(UserRegisterDTO user) {

        UserModel model = UserModel.builder()
                .username(user.username())
                .password(user.password())
                .email(user.email())
                .build();

        return userRepository.save(model).getId();
    }


    public UserDTO updateUser(UserModel newUserData) {
        getUser(newUserData.getId());

        UserModel savedUser = userRepository.save(newUserData);

        return UserDTO.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
    }

    public UUID deleteUser(UUID userid) {
        getUser(userid);
        userRepository.deleteById(userid);

        return userid;
    }
}
