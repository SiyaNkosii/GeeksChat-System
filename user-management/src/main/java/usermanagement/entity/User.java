package usermanagement.entity;

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

    @Column(name = "email", unique = true)
    private String email;

    private String password;
    @OneToMany(mappedBy = "sender")

    private List<Conversation> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<Conversation> receivedMessages;



}
