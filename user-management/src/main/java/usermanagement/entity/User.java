package usermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    private String password;

    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Conversation> sentConversations;

    @JsonIgnore

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Conversation> receivedConversations;

}
