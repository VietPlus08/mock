package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    List<SanPham> findByStatusTrue();

    @Query("select s.maSanPham, s.tenSanPham from SanPham s join ChiTietHoaDonBanHang c join HoaDonBanHang h where h.thoiGianBanHang < ?1 and h.thoiGianBanHang > ?2")
    List<SanPham> findByThoiGian(String thoiGianBatDau, String thoiGianKetThuc);

}
