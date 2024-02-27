package usermanagement.requestPayloads;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    private String senderUsername;

    private String receiverUsername;

    private String message;
}



