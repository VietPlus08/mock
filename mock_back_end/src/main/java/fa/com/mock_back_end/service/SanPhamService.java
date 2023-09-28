package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.SanPhamDTO;
import fa.com.mock_back_end.entity.SanPham;

import java.util.List;
import java.util.Optional;

public interface SanPhamService {

    List<SanPhamDTO> findAllDTO();
    List<SanPham> findAll();
    Optional<SanPham> findById(Long id);
    SanPham save(SanPham sanPham);
    SanPham delete(Long id);
    SanPham update(SanPham sanPhamData);
}
