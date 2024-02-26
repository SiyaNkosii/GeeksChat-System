package usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usermanagement.entity.Contact;
import usermanagement.entity.User;
import usermanagement.requestPayloads.UserLoginRequest;
import usermanagement.requestPayloads.UserRegistrationRequest;
import usermanagement.responsePayloads.ApiResponse;
import usermanagement.responsePayloads.LoginResponse;
import usermanagement.service.serviceImpl.UserServiceImpl;

import java.util.List;

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
    @GetMapping("/searchUsers/{username}")
    public ResponseEntity<List<User>> searchUsers(@PathVariable String username){
        List<User> foundUsers = userService.searchUsers(username);
        return  ResponseEntity.ok(foundUsers);
    }
    @PostMapping("/users/add-contact/{loggedusername}/{searchedusername}")
    public ResponseEntity<Contact> addUserToMyContact(@PathVariable String loggedusername, @PathVariable String searchedusername) {
        return ResponseEntity.ok(userService.addToMyContact(loggedusername,searchedusername));
    }
    @GetMapping("/{loggedInUsername}/List")
    public ResponseEntity<List<Contact>> getChatList(@PathVariable String loggedInUsername){
        List<Contact> chatList = userService.getChatListForloggedInUser(loggedInUsername);
        System.out.println(chatList.toString());

        if(chatList == null || chatList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return  new ResponseEntity<>(chatList, HttpStatus.OK);
    }

}
