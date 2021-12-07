package iuh.kttkpm.nhom11.service;


import io.github.resilience4j.retry.annotation.Retry;
import iuh.kttkpm.nhom11.dto.ProductSupplier;
import iuh.kttkpm.nhom11.dto.Supplier;
import iuh.kttkpm.nhom11.entity.Product;
import iuh.kttkpm.nhom11.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${supplier.url}")
    private String supplierUrl;

    @Cacheable("productSupplierCache")
    @Retry(name = "basic")
    public ProductSupplier findById(Long id) {
        log.info("get By ID");
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            var product = productOptional.get();
            var supplier = restTemplate.getForObject(supplierUrl + "/" + product.getSupplierId(), Supplier.class);
            return ProductSupplier
                    .builder()
                    .product(product)
                    .supplier(supplier)
                    .build();
        }
        return null;
    }

    @CachePut("productSupplierCache")
    @Retry(name = "basic")
    public ProductSupplier putCache(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            var product = productOptional.get();
            var supplier = restTemplate.getForObject(supplierUrl + "/" + product.getSupplierId(), Supplier.class);
            return ProductSupplier
                    .builder()
                    .product(product)
                    .supplier(supplier)
                    .build();
        }
        return null;
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product, Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            product.setId(id);
        }
        return productRepository.save(product);
    }

    public Product delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return productOptional.get();
        }
        return null;
    }

}
