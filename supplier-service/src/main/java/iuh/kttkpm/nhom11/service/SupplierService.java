package iuh.kttkpm.nhom11.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import iuh.kttkpm.nhom11.entity.Supplier;
import iuh.kttkpm.nhom11.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @RateLimiter(name = "multipleRateLimiters_rps_limiter")
    public Supplier findById(Long id) {
        log.info("get supplier");
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        return supplierOptional.orElse(null);
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier update(Supplier supplier, Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            supplier.setId(id);
        }
        return supplierRepository.save(supplier);
    }

    public Supplier delete(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            supplierRepository.deleteById(id);
            return supplierOptional.get();
        }
        return null;
    }

}
