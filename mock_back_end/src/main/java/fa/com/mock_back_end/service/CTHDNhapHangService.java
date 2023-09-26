package fa.com.mock_back_end.service;

import fa.com.mock_back_end.entity.ChiTietHoaDonNhapHang;

import java.util.List;
import java.util.Optional;

public interface CTHDNhapHangService {

    List<ChiTietHoaDonNhapHang> findAll();
    Optional<ChiTietHoaDonNhapHang> findById(Long id);
    ChiTietHoaDonNhapHang save(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang);
    ChiTietHoaDonNhapHang update(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang);
}
