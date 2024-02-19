package usermanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "conversations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conversation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "convId")
    private Long convId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")

    private User receiver;

    private String message;

}
