package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.NhapHangDTO;
import fa.com.mock_back_end.service.HDNHService;
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
@RequestMapping("/staff/nhap_hang")
@CrossOrigin("http://localhost:3000/")
public class NhapHangController {

    @Autowired
    HDNHService HDNHService;

    /**
     * Trả về response gồm list NhapHangDTO
     * @Param
     * @Return List<NhapHangDTO>
     */
    @GetMapping("")
    public ResponseEntity<List<NhapHangDTO>> getList() {
        return ResponseEntity.ok().body(HDNHService.findAllDTO());
    }

    /**
     * Thực hiện lưu đối tượng HoaDonNhapHang từ thông tin request gửi đến
     * @Param nhapHangDTO
     * @Return HoaDonNhapHang
     */
    @PostMapping(value = "")
    public ResponseEntity<NhapHangDTO> createItem(@Valid @RequestBody NhapHangDTO nhapHang) {
        return ResponseEntity.ok().body(HDNHService.save(nhapHang));
    }

    /**
     * Thực hiện cập nhật đối tượng HoaDonNhapHang từ thông tin request gửi đến
     * @Param nhapHangDTO
     * @Return HoaDonNhapHang
     */
    @PutMapping(value = "")
    public ResponseEntity<NhapHangDTO> updateItem(@Valid @RequestBody NhapHangDTO nhapHangDTO) {
        NhapHangDTO nhapHangDTOResponse = HDNHService.update(nhapHangDTO);
        return nhapHangDTOResponse == null
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok().body(nhapHangDTOResponse);
    }
}
