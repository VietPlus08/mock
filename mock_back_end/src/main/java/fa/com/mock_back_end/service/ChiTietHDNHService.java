package fa.com.mock_back_end.service;

import fa.com.mock_back_end.entity.ChiTietHoaDonNhapHang;

import java.util.List;
import java.util.Optional;

public interface ChiTietHDNHService {

    List<ChiTietHoaDonNhapHang> findAll();
    Optional<ChiTietHoaDonNhapHang> findById(Long id);
    ChiTietHoaDonNhapHang save(ChiTietHoaDonNhapHang item);
    void saveAll(List<ChiTietHoaDonNhapHang> listData);
    ChiTietHoaDonNhapHang delete(Long id);
    ChiTietHoaDonNhapHang update(ChiTietHoaDonNhapHang item);
}
