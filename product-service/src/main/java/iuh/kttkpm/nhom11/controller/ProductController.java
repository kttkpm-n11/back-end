package iuh.kttkpm.nhom11.controller;

import io.swagger.annotations.ApiOperation;
import iuh.kttkpm.nhom11.dto.ProductSupplier;
import iuh.kttkpm.nhom11.entity.Product;
import iuh.kttkpm.nhom11.service.ProductService;
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
@RequestMapping("/api/products")
//@CrossOrigin("${spring.security.cross_origin}")
public class ProductController {

    @Autowired
    private ProductService productService;



    @GetMapping("/{id}")
    @ApiOperation("Xem chi tiết sản phẩm theo mã")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/put/{id}")
    @ApiOperation("...")
    public ResponseEntity<?> putCache(@PathVariable Long id) {
        return ResponseEntity.ok(productService.putCache(id));
    }

    @GetMapping
    @ApiOperation("Lấy tất cả sản phẩm")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    @ApiOperation("Thêm một sản phẩm mới")
    public ResponseEntity<?> save(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{id}")
    @ApiOperation("Cập nhật sản phẩm")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id) {
        return ResponseEntity.ok(productService.update(product, id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Xóa sản phẩm theo id")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(productService.delete(id));
    }


}
