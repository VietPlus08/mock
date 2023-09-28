package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.KhachHangDTO;
import fa.com.mock_back_end.entity.KhachHang;
import fa.com.mock_back_end.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/khach_hang")
@CrossOrigin("http://localhost:3000/")
public class KhachHangController {

    @Autowired
    KhachHangService khachHangService;

    @GetMapping("")
    public ResponseEntity<List<KhachHangDTO>> getList(){
        return ResponseEntity.ok().body(khachHangService.findAllDTO());
    }

    @DeleteMapping("")
    public ResponseEntity<KhachHang> deleteItem(@RequestParam("id") Long id) {
        KhachHang khachHang = khachHangService.delete(id);
        if (khachHang != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/edit")
    public ResponseEntity<KhachHang> findItem(@RequestParam("id") Long id) {
        Optional<KhachHang> khachHang = khachHangService.findById(id);
        return khachHang.map(item -> ResponseEntity.ok().body(item))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "")
    public ResponseEntity<KhachHang> createItem(@Valid @RequestBody KhachHang khachHang) {
        return ResponseEntity.ok().body(khachHangService.save(khachHang));
    }

    @PutMapping(value = "")
    public ResponseEntity<KhachHang> updateItem(@Valid @RequestBody KhachHang khachHang) {
        return ResponseEntity.ok().body(khachHangService.update(khachHang));
    }
}
