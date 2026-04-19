package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.dto.request.CreateUserRequest;
import com.eventbooking.eventbookingsystem.dto.response.UserDTO;
import com.eventbooking.eventbookingsystem.entity.User;
import com.eventbooking.eventbookingsystem.mapper.EntityMapper;
import com.eventbooking.eventbookingsystem.service.UserService;
import com.eventbooking.eventbookingsystem.wrapper.APIResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public APIResponse<UserDTO> createUser(@Valid @RequestBody CreateUserRequest request) {

        User createdUser = userService.createUser(request.getName(), request.getEmail());

        return new APIResponse<>(
                true,
                "User created successfully",
                EntityMapper.toUserDTO(createdUser)
        );
    }

    @GetMapping
    public APIResponse<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers()
                .stream()
                .map(EntityMapper::toUserDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Users fetched",
                users
        );

    }

    @GetMapping("/{id}")
    public APIResponse<UserDTO> getUserById(@PathVariable Long id) {

        Optional<User> user = userService.getUserById(id);
        if(user.isEmpty()){
            throw new RuntimeException("User not found: "+id);
        }

        return new APIResponse<>(
                true,
                "User fetched",
                EntityMapper.toUserDTO(user.get())
        );
    }

    @PutMapping("/{id}")
    public APIResponse<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {

        User updatedUser = userService.updateUser(id, user);

        return new APIResponse<>(
                true,
                "User updated successfully",
                EntityMapper.toUserDTO(updatedUser)
        );
    }

    @DeleteMapping("/{id}")
    public APIResponse<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return new APIResponse<>(
                true,
                "User deleted successfully",
                null
        );
    }
}
