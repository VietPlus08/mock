package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.SanPhamDTO;
import fa.com.mock_back_end.entity.SanPham;

import java.util.List;
import java.util.Optional;

public interface SanPhamService {

    List<SanPhamDTO> findAllDTO();
    List<SanPham> findAll();
    Optional<SanPham> findById(Long id);
    Optional<SanPhamDTO> findDTOById(Long id);
    SanPhamDTO save(SanPhamDTO sanPham);
    SanPhamDTO delete(Long id);
    SanPhamDTO update(SanPhamDTO data);
    void updateInventory(long maSanPham, int soLuong);
    SanPham findByTenSanPham(String tenSanPham);
    SanPham save(SanPham sanPham);
}
