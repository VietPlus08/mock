package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.ThongKeDTO;
import fa.com.mock_back_end.service.impl.ThongKeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thong_ke")
@CrossOrigin("http://localhost:3000/")
public class ThongKeController {

    @Autowired
    ThongKeServiceImpl thongKeService;

    @PostMapping("")
    public ResponseEntity<ThongKeDTO> getThongKe(@RequestBody ThongKeDTO thongKeDTO){
        return ResponseEntity.ok(thongKeService.getThongKe(thongKeDTO));
    }

}
