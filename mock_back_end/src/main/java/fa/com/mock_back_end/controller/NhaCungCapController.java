package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.NhaCungCapDTO;
import fa.com.mock_back_end.entity.HoaDonNhapHang;
import fa.com.mock_back_end.entity.NhaCungCap;
import fa.com.mock_back_end.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/nha_cung_cap")
@CrossOrigin("http://localhost:3000/")
public class NhaCungCapController {

    @Autowired
    NhaCungCapService nhaCungCapService;

    @GetMapping("")
    public ResponseEntity<List<NhaCungCapDTO>> getList(){
        return ResponseEntity.ok().body(nhaCungCapService.findAllDTO());
    }

    @DeleteMapping("")
    public ResponseEntity<NhaCungCap> deleteItem(@RequestParam("id") Long id) {
        NhaCungCap nhaCungCap = nhaCungCapService.delete(id);
        if (nhaCungCap != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/edit")
    public ResponseEntity<NhaCungCap> findItem(@RequestParam("id") Long id) {
        Optional<NhaCungCap> nhaCungCap = nhaCungCapService.findById(id);
        return nhaCungCap.map(item -> ResponseEntity.ok().body(item))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "")
    public ResponseEntity<NhaCungCap> createItem(@Valid @RequestBody NhaCungCap nhaCungCap) {
        return ResponseEntity.ok().body(nhaCungCapService.save(nhaCungCap));
    }

    @PutMapping(value = "")
    public ResponseEntity<NhaCungCap> updateItem(@Valid @RequestBody NhaCungCap nhaCungCap) {
        return ResponseEntity.ok().body(nhaCungCapService.update(nhaCungCap));
    }
}
