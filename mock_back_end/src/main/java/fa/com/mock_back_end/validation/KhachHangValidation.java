package fa.com.mock_back_end.validation;

import fa.com.mock_back_end.dto.KhachHangDTO;
import fa.com.mock_back_end.entity.KhachHang;
import fa.com.mock_back_end.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KhachHangValidation {

    @Autowired
    KhachHangRepository khachHangRepository;

    public Map<String, String> validate(KhachHangDTO khachHang) {
        Map<String, String> errors = new HashMap<>();
        KhachHang findKhachHang = khachHangRepository.findBySoDienThoai(khachHang.getSoDienThoai());
        if (findKhachHang != null){
            errors.put("soDienThoai","Số điện thoại đã tồn tại");
        }
        return errors;
    }
}
