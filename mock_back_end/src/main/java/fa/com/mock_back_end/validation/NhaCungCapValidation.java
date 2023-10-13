package fa.com.mock_back_end.validation;

import fa.com.mock_back_end.dto.NhaCungCapDTO;
import fa.com.mock_back_end.entity.NhaCungCap;
import fa.com.mock_back_end.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class NhaCungCapValidation {

    @Autowired
    NhaCungCapRepository nhaCungCapRepository;

    public Map<String, String> validateCreate(NhaCungCapDTO nhaCungCap) {
        Map<String, String> errors = new HashMap<>();
        NhaCungCap findNhaCungCap = nhaCungCapRepository.findBySoDienThoai(nhaCungCap.getSoDienThoai());
        if (findNhaCungCap != null) {
            errors.put("soDienThoai", "Số điện thoại đã tồn tại");
        }
        return errors;
    }
    public Map<String, String> validateUpdate(NhaCungCapDTO nhaCungCap) {
        Map<String, String> errors = new HashMap<>();
        NhaCungCap findNhaCungCap = nhaCungCapRepository.findBySoDienThoai(nhaCungCap.getSoDienThoai());
        if (findNhaCungCap != null && !Objects.equals(findNhaCungCap.getMaNhaCungCap(), nhaCungCap.getMaNhaCungCap())) {
            errors.put("soDienThoai", "Số điện thoại đã tồn tại");
        }
        return errors;
    }
}
