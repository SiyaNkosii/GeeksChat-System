package usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import usermanagement.requestPayloads.UserLoginRequest;
import usermanagement.requestPayloads.UserRegistrationRequest;
import usermanagement.responsePayloads.ApiResponse;
import usermanagement.responsePayloads.LoginResponse;
import usermanagement.service.serviceImpl.UserServiceImpl;

@RestController
public class UserController {
    @Autowired
    public UserServiceImpl userService;

    @PostMapping("/users/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserRegistrationRequest request) {
        boolean isRegistered = userService.registerUser(request);
        if (isRegistered) {
            return ResponseEntity.ok(new ApiResponse("Successfully registered"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("Failed to register"));
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody UserLoginRequest request) {
        LoginResponse loginResponse = userService.loginUser(request.getEmail(), request.getPassword());
        if (loginResponse != null) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.badRequest().body(new LoginResponse());
        }

    }
}
