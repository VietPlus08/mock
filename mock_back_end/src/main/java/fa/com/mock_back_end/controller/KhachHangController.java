package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.KhachHangDTO;
import fa.com.mock_back_end.service.KhachHangService;
import fa.com.mock_back_end.validation.KhachHangValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/staff/khach_hang")
@CrossOrigin("http://localhost:3000/")
public class KhachHangController {

    @Autowired
    KhachHangService khachHangService;

    @Autowired
    KhachHangValidation khachHangValidation;

    @GetMapping("")
    public ResponseEntity<List<KhachHangDTO>> getList() {
        return ResponseEntity.ok().body(khachHangService.findAllDTO());
    }

    @DeleteMapping("")
    public ResponseEntity<KhachHangDTO> deleteItem(@RequestParam("id") Long id) {
        KhachHangDTO khachHang = khachHangService.delete(id);
        if (khachHang != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createItem(@Valid @RequestBody KhachHangDTO khachHang) {
        Map<String, String> errors = khachHangValidation.validate(khachHang);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        return ResponseEntity.ok().body(khachHangService.save(khachHang));
    }

    @PutMapping(value = "")
    public ResponseEntity<KhachHangDTO> updateItem(@Valid @RequestBody KhachHangDTO khachHang) {
        return ResponseEntity.ok().body(khachHangService.update(khachHang));
    }
}
