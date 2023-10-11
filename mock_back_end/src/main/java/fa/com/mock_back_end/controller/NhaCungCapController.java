package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.NhaCungCapDTO;
import fa.com.mock_back_end.service.NhaCungCapService;
import fa.com.mock_back_end.validation.NhaCungCapValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/staff/nha_cung_cap")
@CrossOrigin("http://localhost:3000/")
public class NhaCungCapController {

    @Autowired
    NhaCungCapService nhaCungCapService;

    @Autowired
    NhaCungCapValidation nhaCungCapValidation;

    @GetMapping("")
    public ResponseEntity<List<NhaCungCapDTO>> getList() {
        return ResponseEntity.ok().body(nhaCungCapService.findAllDTO());
    }

    @DeleteMapping("")
    public ResponseEntity<NhaCungCapDTO> deleteItem(@RequestParam("id") Long id) {
        NhaCungCapDTO nhaCungCap = nhaCungCapService.delete(id);
        if (nhaCungCap != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/edit")
    public ResponseEntity<NhaCungCapDTO> findItem(@RequestParam("id") Long id) {
        Optional<NhaCungCapDTO> nhaCungCap = nhaCungCapService.findDTOById(id);
        return nhaCungCap.map(item -> ResponseEntity.ok().body(item))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createItem(@Valid @RequestBody NhaCungCapDTO nhaCungCap) {
        Map<String, String> errors = nhaCungCapValidation.validateCreate(nhaCungCap);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok().body(nhaCungCapService.save(nhaCungCap));
    }

    @PutMapping(value = "")
    public ResponseEntity<?> updateItem(@Valid @RequestBody NhaCungCapDTO nhaCungCap) {
        Map<String, String> errors = nhaCungCapValidation.validateUpdate(nhaCungCap);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok().body(nhaCungCapService.update(nhaCungCap));
    }
}
