package iuh.kttkpm.nhom11.controller;

import io.swagger.annotations.ApiOperation;
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
//@CrossOrigin("${spring.security.cross_origin}")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/{id}")
    @ApiOperation("Xem chi tiết nhà cung cấp theo id")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.findById(id));
    }

    @GetMapping
    @ApiOperation("Lấy tất cả nhà cung cấp")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    @PostMapping
    @ApiOperation("Thêm nhà cung cấp mới")
    public ResponseEntity<?> save(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.save(supplier));
    }

    @PutMapping("/{id}")
    @ApiOperation("Cập nhật thông tin nhà cung cấp")
    public ResponseEntity<?> update(@RequestBody Supplier supplier, @PathVariable Long id) {
        return ResponseEntity.ok(supplierService.update(supplier, id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Xóa nhà cung cấp")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.delete(id));
    }

}
