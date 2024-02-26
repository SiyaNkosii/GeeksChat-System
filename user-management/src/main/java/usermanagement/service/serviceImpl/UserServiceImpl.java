package usermanagement.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usermanagement.entity.Contact;
import usermanagement.entity.User;
import usermanagement.exceptions.IllegalArgumentException;
import usermanagement.exceptions.ResourceAlreadyExistException;
import usermanagement.exceptions.ResourceNotFoundException;
import usermanagement.repository.ContactRepository;
import usermanagement.repository.UserRepository;
import usermanagement.requestPayloads.UserRegistrationRequest;
import usermanagement.responsePayloads.LoginResponse;
import usermanagement.service.UserService;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public ContactRepository contactRepository;
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

    @Override
    public List<User> searchUsers(String username) {
        List<User> users=userRepository.findByUsernameContaining(username);
        if(!users.isEmpty()){
            return users;
        }else {
            log.error("unable to find username: {}",username);
            throw new ResourceNotFoundException("Could not find contact with username :"+username);
        }
    }

    @Override
    public Contact addToMyContact(String loggedInUser, String searchedUsers) {
        User loggedinUser= userRepository.findByUsername(loggedInUser);
        User searchedUser= userRepository.findByUsername(searchedUsers);

        if(loggedInUser != null && searchedUser !=null) {
            if(loggedInUser.equals(searchedUsers)){
                throw new IllegalArgumentException("Cannot add yourself as a contact.");
            }
            List<Contact> existingContacts = contactRepository.findByUser(loggedinUser);
            for (Contact contact : existingContacts) {
                if (contact.getContactUser().getUsername().equals(searchedUsers)) {
                    throw new ResourceAlreadyExistException("Contact is already part of your contacts.");
                }
            }


            Contact contact = Contact.builder()
                    .user(loggedinUser)
                    .contactUser(searchedUser)
                    .build();
            log.info("Saving contact with username: {} to my contact", searchedUser);
            contactRepository.save(contact);


            return contact;
        }else {
            throw new ResourceNotFoundException("Logged-in user or searched user not found.");
        }    }

    @Override
    public List<Contact> getChatListForloggedInUser(String loggedInUsername) {
        User loggedInUser = userRepository.findByUsername(loggedInUsername);
        if(loggedInUser !=null){
            return contactRepository.findByUser(loggedInUser);
        } else {
            return Collections.emptyList();
        }
    }
    public User SelectedUsername(String selectedUsername) {
        User selectedUser = userRepository.findByUsername(selectedUsername);
        if(selectedUser !=null){
            return selectedUser;
        }
        return null;
    }

}
