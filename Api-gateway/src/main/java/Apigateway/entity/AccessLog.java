package Apigateway.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "access_logs")
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "username")
    private String username;

    @Column(name = "access_time")
    @CreatedDate
    private LocalDateTime accessTime;

    public AccessLog() {

    }


}
