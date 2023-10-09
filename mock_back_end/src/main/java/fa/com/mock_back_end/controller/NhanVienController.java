package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.NhanVienDTO;
import fa.com.mock_back_end.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/nhan_vien")
@CrossOrigin("http://localhost:3000/")
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("")
    public ResponseEntity<List<NhanVienDTO>> getList() {
        return ResponseEntity.ok().body(nhanVienService.findAllDTO());
    }

    @DeleteMapping("")
    public ResponseEntity<NhanVienDTO> deleteItem(@RequestParam("id") String id) {
        NhanVienDTO nhanVien = nhanVienService.delete(id);
        if (nhanVien != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "")
    public ResponseEntity<NhanVienDTO> createItem(@Valid @RequestBody NhanVienDTO nhanVien) {
        return ResponseEntity.ok().body(nhanVienService.save(nhanVien));
    }

    @PutMapping(value = "")
    public ResponseEntity<NhanVienDTO> updateItem(@Valid @RequestBody NhanVienDTO updateNhanVien) {
        Map<String, String> errors = new HashMap<>();
        return ResponseEntity.ok().body(nhanVienService.update(updateNhanVien));
    }
}
