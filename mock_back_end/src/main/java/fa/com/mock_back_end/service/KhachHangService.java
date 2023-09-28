package fa.com.mock_back_end.service;

import fa.com.mock_back_end.entity.KhachHang;

import java.util.List;
import java.util.Optional;

public interface KhachHangService {

    List<KhachHang> findAll();
    Optional<KhachHang> findById(Long id);

    KhachHang findByStatusTrueAndSoDienThoai(String soDienThoai);
    KhachHang save(KhachHang item);
    KhachHang delete(Long id);
    KhachHang update(KhachHang item);
}
