package iuh.kttkpm.nhom11;

import iuh.kttkpm.nhom11.entity.Supplier;
import iuh.kttkpm.nhom11.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "supplier.insert")
public class InsertSupplierData implements CommandLineRunner {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 10; i++) {
            var supplier = Supplier.builder()
                    .id(String.valueOf(i))
                    .name("supplier " + i)
                    .address("address supplier " + i)
                    .email("email supplier " + i)
                    .phone("phone supplier " + i)
                    .build();
            supplierRepository.save(supplier);
        }
    }

}
