package iuh.kttkpm.nhom11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {
    private String field;
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
