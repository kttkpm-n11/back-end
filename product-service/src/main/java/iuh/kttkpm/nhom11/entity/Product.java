package iuh.kttkpm.nhom11.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    private String id;

    private String name;
    private double price;
    private double discount;
    private String origin;
    private double tax;
    private String description;
    private String material;
    private boolean active;

    @CreatedDate
    private LocalDateTime createdAt;

    private String supplierId;

}
