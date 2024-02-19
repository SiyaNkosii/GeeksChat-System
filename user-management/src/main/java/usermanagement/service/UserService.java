package usermanagement.service;

import org.springframework.stereotype.Service;
import usermanagement.requestPayloads.UserLoginRequest;
import usermanagement.requestPayloads.UserRegistrationRequest;
import usermanagement.responsePayloads.LoginResponse;

@Service
public interface UserService {

    boolean registerUser(UserRegistrationRequest userRequest);
    LoginResponse loginUser(String email, String password);

}
