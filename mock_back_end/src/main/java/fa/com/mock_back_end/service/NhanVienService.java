package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.NhanVienDTO;
import fa.com.mock_back_end.entity.NhanVien;

import java.util.List;
import java.util.Optional;

public interface NhanVienService {

    List<NhanVienDTO> findAllDTO();
    List<NhanVien> findAllStatusTrue();
    List<NhanVien> findAll();
    Optional<NhanVien> findById(String id);
    NhanVienDTO save(NhanVienDTO nhanVien);
    NhanVienDTO delete(String id);
    NhanVienDTO update(NhanVienDTO nhanVien);
}
