package fa.com.mock_back_end.validation;

import fa.com.mock_back_end.dto.KhachHangDTO;
import fa.com.mock_back_end.dto.NhanVienDTO;
import fa.com.mock_back_end.entity.KhachHang;
import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.repository.KhachHangRepository;
import fa.com.mock_back_end.repository.NhanVienRepository;
import fa.com.mock_back_end.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        Optional<NhanVien> nhanVien = nhanVienService.findById(nhanVienDTO.getMaNhanVien());
        if (!Objects.equals(nhanVienDTO.getPassword(), nhanVienDTO.getNewPassword())) {
            errors.put("rePassword", "Password không trùng nhau!");
        }
        if (nhanVien.isPresent()
                && passwordEncoder.matches(nhanVienDTO.getPassword(), nhanVien.get().getMatKhau())) {
            errors.put("password", "Bạn hay thay đổi password!");
        }
        return errors;
    }
}
