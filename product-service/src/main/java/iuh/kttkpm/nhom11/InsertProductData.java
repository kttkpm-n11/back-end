package iuh.kttkpm.nhom11;

import iuh.kttkpm.nhom11.entity.Product;
import iuh.kttkpm.nhom11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(name = "product.insert")
public class InsertProductData implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 10; i++) {
            var product = Product.builder()
                    .id(String.valueOf(i))
                    .description("description " + i)
                    .material("material " + i)
                    .name("product name " + i)
                    .origin("origin " + i)
                    .createdAt(LocalDateTime.now())
                    .supplierId(String.valueOf(i))
                    .build();
            productRepository.save(product);
        }
    }
}
