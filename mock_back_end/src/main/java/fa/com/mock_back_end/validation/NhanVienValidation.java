package fa.com.mock_back_end.validation;

import fa.com.mock_back_end.dto.NhanVienDTO;
import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class NhanVienValidation {

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Map<String, String> validate(NhanVienDTO nhanVienDTO) {
        Map<String, String> errors = new HashMap<>();
        // Trườngg hợp password nhận về = null -> cần update account infor
        if (nhanVienDTO.getPassword() == null) {
            if (nhanVienDTO.getNgaySinh() != null
                    && nhanVienDTO.getNgaySinh().isAfter(LocalDate.now())) {
                errors.put("ngaySinh", "Ngày sinh phải trước ngày hiện tại");
            }
            return errors;
        }
        // Trườngg hợp password nhận về khác null -> cần update password
        Optional<NhanVien> nhanVien = nhanVienService.findById(nhanVienDTO.getMaNhanVien());
        if (!Objects.equals(nhanVienDTO.getPassword(), nhanVienDTO.getRePassword())) {
            errors.put("rePassword", "Password không trùng nhau!");
        }
        if (nhanVien.isPresent()
                && passwordEncoder.matches(nhanVienDTO.getPassword(), nhanVien.get().getMatKhau())) {
            errors.put("password", "Bạn hay thay đổi password!");
        }
        if (nhanVienDTO.getNgaySinh().isAfter(LocalDate.now())){
            errors.put("ngaySinh","Ngày sinh phải trước ngày hiện tại");
        }
        return errors;
    }
}
