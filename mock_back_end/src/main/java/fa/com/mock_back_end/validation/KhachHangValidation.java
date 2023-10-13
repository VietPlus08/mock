package fa.com.mock_back_end.validation;

import fa.com.mock_back_end.dto.KhachHangDTO;
import fa.com.mock_back_end.entity.KhachHang;
import fa.com.mock_back_end.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class KhachHangValidation {

    @Autowired
    KhachHangRepository khachHangRepository;

    public Map<String, String> validateCreate(KhachHangDTO khachHang) {
        Map<String, String> errors = new HashMap<>();
        KhachHang findKhachHang = khachHangRepository.findBySoDienThoai(khachHang.getSoDienThoai());
        if (findKhachHang != null){
            errors.put("soDienThoai","Số điện thoại đã tồn tại");
        }
        if (khachHang.getNgaySinh().isAfter(LocalDate.now())){
            errors.put("ngaySinh","Ngày sinh phải trước ngày hiện tại");
        }
        return errors;
    }

    public Map<String, String> validateUpdate(KhachHangDTO khachHang) {
        Map<String, String> errors = new HashMap<>();
        KhachHang findKhachHang = khachHangRepository.findBySoDienThoai(khachHang.getSoDienThoai());
        if (findKhachHang != null && !Objects.equals(findKhachHang.getMaKhachHang(), khachHang.getMaKhachHang())){
            errors.put("soDienThoai","Số điện thoại đã tồn tại");
        }
        if (khachHang.getNgaySinh().isAfter(LocalDate.now())){
            errors.put("ngaySinh","Ngày sinh phải trước ngày hiện tại");
        }
        return errors;
    }
}
