package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiTietHDBHRepository extends JpaRepository<ChiTietHoaDonBanHang, Long> {

    public List<ChiTietHoaDonBanHang> findByStatusTrue();
}
