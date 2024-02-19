package usermanagement.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usermanagement.entity.User;
import usermanagement.repository.UserRepository;
import usermanagement.requestPayloads.UserRegistrationRequest;
import usermanagement.responsePayloads.LoginResponse;
import usermanagement.service.UserService;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepository userRepository;
    @Override
    public boolean registerUser(UserRegistrationRequest userRequest){
        User existingUser = userRepository.findByEmail(userRequest.getEmail());
        if(existingUser !=null){
            return false;
        }
        User NewUser= new User();
        NewUser.setUsername(userRequest.getUsername());
        NewUser.setEmail(userRequest.getEmail());
        NewUser.setPassword(userRequest.getPassword());
        userRepository.save(NewUser);
        return true;
    }

    @Override
    public LoginResponse loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user !=null && user.getPassword().equals(password)){
            return new LoginResponse(true,  "Successfully logged in", user.getUsername());
        }
        return new LoginResponse(false, "Invalid credentials"," ");
    }
}
