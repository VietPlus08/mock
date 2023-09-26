package fa.com.mock_back_end.service;

import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;

import java.util.List;
import java.util.Optional;

public interface ChiTietHDBHService {

    List<ChiTietHoaDonBanHang> findAll();
    Optional<ChiTietHoaDonBanHang> findById(Long id);
    ChiTietHoaDonBanHang save(ChiTietHoaDonBanHang item);
    ChiTietHoaDonBanHang delete(Long id);
    ChiTietHoaDonBanHang update(ChiTietHoaDonBanHang item);
}
