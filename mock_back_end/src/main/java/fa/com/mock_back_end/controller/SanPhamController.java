package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/san_pham")
@CrossOrigin("http://localhost:3000/")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("/init")
    public void setInit(){
        sanPhamService.save(new SanPham(null,"1","1","3",1L,1L,"Vang","32GB","Tô","url..."));
    }

    @GetMapping("")
    public ResponseEntity<List<SanPham>> getList(){
        return ResponseEntity.ok().body(sanPhamService.findAll());
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
