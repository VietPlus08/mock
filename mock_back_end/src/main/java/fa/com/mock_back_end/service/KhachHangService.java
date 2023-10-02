package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.KhachHangDTO;
import fa.com.mock_back_end.entity.KhachHang;

import java.util.List;
import java.util.Optional;

public interface KhachHangService {

    List<KhachHangDTO> findAllDTO();
    List<KhachHang> findAll();
    Optional<KhachHangDTO> findDTOById(Long id);
    Optional<KhachHang> findById(Long id);
    KhachHangDTO save(KhachHangDTO item);
    KhachHangDTO delete(Long id);
    KhachHangDTO update(KhachHangDTO item);
}
