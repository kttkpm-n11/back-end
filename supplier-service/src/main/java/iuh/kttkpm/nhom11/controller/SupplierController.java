package iuh.kttkpm.nhom11.controller;

import iuh.kttkpm.nhom11.entity.Supplier;
import iuh.kttkpm.nhom11.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.save(supplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Supplier supplier, @PathVariable Long id) {
        return ResponseEntity.ok(supplierService.update(supplier, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.delete(id));
    }

}
