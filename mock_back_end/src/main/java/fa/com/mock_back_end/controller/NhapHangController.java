package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.NhapHangDTO;
import fa.com.mock_back_end.service.HDNHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<List<NhapHangDTO>> getList() {
        return ResponseEntity.ok().body(hdnhService.findAllDTO());
    }

    /**
     * @Description createItem
     * @Param NhapHangDTO
     * @Return HoaDonNhapHang
     */
    @PostMapping(value = "")
    public ResponseEntity<NhapHangDTO> createItem(@Valid @RequestBody NhapHangDTO nhapHang) {
        return ResponseEntity.ok().body(hdnhService.save(nhapHang));
    }

    /**
     * @Description updateItem
     * @Param HoaDonNhapHang
     * @Return HoaDonNhapHang
     */
    @PutMapping(value = "")
    public ResponseEntity<NhapHangDTO> updateItem(@Valid @RequestBody NhapHangDTO updateNhapHangDTO) {
        NhapHangDTO nhapHangDTOResponse = hdnhService.update(updateNhapHangDTO);
        return nhapHangDTOResponse == null
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok().body(nhapHangDTOResponse);
    }
}
