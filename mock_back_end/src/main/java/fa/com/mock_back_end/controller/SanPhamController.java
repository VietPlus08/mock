package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.SanPhamDTO;
import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 9/28/2023
*/
@RestController
@RequestMapping("/san_pham")
@CrossOrigin("http://localhost:3000/")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("")
    public ResponseEntity<List<SanPhamDTO>> getList(){
        return ResponseEntity.ok().body(sanPhamService.findAllDTO());
    }

    @DeleteMapping("")
    public ResponseEntity<SanPham> deleteItem(@RequestParam("id") Long id) {
        SanPham sanPham = sanPhamService.delete(id);
        if (sanPham != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/edit")
    public ResponseEntity<SanPham> findItem(@RequestParam("id") Long id) {
        Optional<SanPham> sanPham = sanPhamService.findById(id);
        return sanPham.map(item -> ResponseEntity.ok().body(item))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "")
    public ResponseEntity<SanPham> createItem(@Valid @RequestBody SanPham sanPham) {
        return ResponseEntity.ok().body(sanPhamService.save(sanPham));
    }

    @PutMapping(value = "")
    public ResponseEntity<SanPham> updateItem(@Valid @RequestBody SanPham updateSanPham) {
        return ResponseEntity.ok().body(sanPhamService.update(updateSanPham));
    }
}
