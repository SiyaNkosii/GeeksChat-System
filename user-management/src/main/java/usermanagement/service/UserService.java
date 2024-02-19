package usermanagement.service;

import org.springframework.stereotype.Service;
import usermanagement.entity.Contact;
import usermanagement.entity.User;
import usermanagement.requestPayloads.UserLoginRequest;
import usermanagement.requestPayloads.UserRegistrationRequest;
import usermanagement.responsePayloads.LoginResponse;

import java.util.List;

@Service
public interface UserService {

    boolean registerUser(UserRegistrationRequest userRequest);
    LoginResponse loginUser(String email, String password);
    List<User> searchUsers(String username);
    Contact addToMyContact(String loggedInUser, String searchedUser);




}
