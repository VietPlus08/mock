package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.ChiTietHoaDonNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiTietHDNHRepository extends JpaRepository<ChiTietHoaDonNhapHang, Long> {

    public List<ChiTietHoaDonNhapHang> findByStatusTrue();
}
