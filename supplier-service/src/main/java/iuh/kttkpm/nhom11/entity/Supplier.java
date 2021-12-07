package iuh.kttkpm.nhom11.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String email;

    @Column(length = 500)
    private String address;

    private String phone;

}
