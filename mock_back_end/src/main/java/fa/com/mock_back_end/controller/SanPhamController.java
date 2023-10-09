package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.SanPhamDTO;
import fa.com.mock_back_end.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author QUANGNA7
 * @Version 1.0
 * @Since 9/28/2023
 */
@RestController
@RequestMapping("/staff/san_pham")
@CrossOrigin("http://localhost:3000/")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;

    /**
     * @Description getList
     * @Param
     * @Return List<SanPhamDTO>
     */
    @GetMapping(value = "/list")
    public ResponseEntity<List<SanPhamDTO>> getList() {
        return ResponseEntity.ok().body(sanPhamService.findAllDTO());
    }

    /**
     * @Description deleteItem
     * @Param id
     * @Return SanPhamDTO
     */
    @DeleteMapping("")
    public ResponseEntity<SanPhamDTO> deleteItem(@RequestParam(value = "id") Long id) {
        SanPhamDTO sanPham = sanPhamService.delete(id);
        if (sanPham != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * @Description createItem
     * @Param sanPham
     * @Return SanPhamDTO
     */
    @PostMapping(value = "")
    public ResponseEntity<SanPhamDTO> createItem(@Valid @RequestBody SanPhamDTO sanPham) {
        return ResponseEntity.ok().body(sanPhamService.save(sanPham));
    }

    /**
     * @Description updateItem
     * @Param sanPham
     * @Return SanPhamDTO
     */
    @PutMapping(value = "")
    public ResponseEntity<SanPhamDTO> updateItem(@Valid @RequestBody SanPhamDTO updateSanPham) {
        SanPhamDTO sanPhamDTOResponse = sanPhamService.update(updateSanPham);
        return sanPhamDTOResponse == null
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok().body(sanPhamDTOResponse);
    }
}
