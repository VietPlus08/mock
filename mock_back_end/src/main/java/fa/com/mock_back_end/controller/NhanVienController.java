package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nhan_vien")
@CrossOrigin("http://localhost:3000/")
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("")
    public ResponseEntity<List<NhanVien>> getList(){
        return ResponseEntity.ok().body(nhanVienService.findAll());
    }

    @DeleteMapping("")
    public ResponseEntity<NhanVien> deleteItem(@RequestParam("id") String id) {
        NhanVien nhanVien = nhanVienService.delete(id);
        if (nhanVien != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/edit")
    public ResponseEntity<NhanVien> findItem(@RequestParam("id") String id) {
        Optional<NhanVien> nhanVien = nhanVienService.findById(id);
        return nhanVien.map(vien -> ResponseEntity.ok().body(vien))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "")
    public ResponseEntity<NhanVien> createItem(@Valid @RequestBody NhanVien nhanVien) {
        return ResponseEntity.ok().body(nhanVienService.save(nhanVien));
    }

    @PutMapping(value = "")
    public ResponseEntity<NhanVien> updateItem(@Valid @RequestBody NhanVien updateNhanVien) {
        return ResponseEntity.ok().body(nhanVienService.update(updateNhanVien));
    }
}
