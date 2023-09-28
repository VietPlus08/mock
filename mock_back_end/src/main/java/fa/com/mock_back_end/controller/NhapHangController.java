package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.NhapHangDTO;
import fa.com.mock_back_end.entity.HoaDonNhapHang;
import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.service.HDNHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 9/28/2023
*/
@Controller
@RequestMapping("/nhap_hang")
@CrossOrigin("http://localhost:3000/")
public class NhapHangController {

    @Autowired
    HDNHService hdnhService;

    /**
    * @Description getList
    * @Param
    * @Return List<NhapHangDTO>
    */
    @GetMapping("")
    public ResponseEntity<List<NhapHangDTO>> getList(){
        return ResponseEntity.ok().body(hdnhService.findAllDTO());
    }

    /**
    * @Description deleteItem
    * @Param id
    * @Return SanPham
    */
    @DeleteMapping("")
    public ResponseEntity<SanPham> deleteItem(@RequestParam("id") Long id) {
        HoaDonNhapHang nhapHang = hdnhService.delete(id);
        if (nhapHang != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
    * @Description findItem
    * @Param id
    * @Return HoaDonNhapHang
    */
    @GetMapping(value = "/edit")
    public ResponseEntity<HoaDonNhapHang> findItem(@RequestParam("id") Long id) {
        Optional<HoaDonNhapHang> nhapHang = hdnhService.findById(id);
        return nhapHang.map(item -> ResponseEntity.ok().body(item))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
    * @Description createItem
    * @Param NhapHangDTO
    * @Return HoaDonNhapHang
    */

    @PostMapping(value = "")
    public ResponseEntity<HoaDonNhapHang> createItem(@Valid @RequestBody NhapHangDTO nhapHang) {
        return ResponseEntity.ok().body(hdnhService.save(nhapHang));
    }

    /**
    * @Description updateItem
    * @Param HoaDonNhapHang
    * @Return HoaDonNhapHang
    */
    @PutMapping(value = "")
    public ResponseEntity<HoaDonNhapHang> updateItem(@Valid @RequestBody HoaDonNhapHang hoaDonNhapHang) {
        return ResponseEntity.ok().body(hdnhService.update(hoaDonNhapHang));
    }
}
