package iuh.kttkpm.nhom11.repository;

import iuh.kttkpm.nhom11.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
}
