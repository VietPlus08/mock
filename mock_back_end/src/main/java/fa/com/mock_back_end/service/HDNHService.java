package fa.com.mock_back_end.service;

import fa.com.mock_back_end.entity.HoaDonNhapHang;

import java.util.List;
import java.util.Optional;

public interface HDNHService {

    List<HoaDonNhapHang> findAll();
    Optional<HoaDonNhapHang> findById(Long id);
    HoaDonNhapHang save(HoaDonNhapHang item);
    HoaDonNhapHang delete(Long id);
    HoaDonNhapHang update(HoaDonNhapHang item);
}
