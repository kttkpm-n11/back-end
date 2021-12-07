package iuh.kttkpm.nhom11;

import iuh.kttkpm.nhom11.entity.Product;
import iuh.kttkpm.nhom11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication
@EnableCaching
public class ProductServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 4; i++) {
            var product = Product.builder()
                    .id((long) i)
                    .description("description " + i)
                    .material("material " + i)
                    .name("product name " + i)
                    .origin("origin " + i)
                    .supplierId((long) i)
                    .build();
            productRepository.save(product);
        }
    }

    /*
    config swagger, generate document for api
    */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("iuh.kttkpm.nhom11"))
                .paths(PathSelectors.any())
                .build();
    }

}
