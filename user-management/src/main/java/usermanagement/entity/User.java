package usermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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

    private List<Conversation> sentConversations;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Conversation> receivedConversations;

}
