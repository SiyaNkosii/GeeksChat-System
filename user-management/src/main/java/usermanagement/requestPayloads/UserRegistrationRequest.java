package usermanagement.requestPayloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationRequest {
    private String username;
    private String email;
    private String password;
}
