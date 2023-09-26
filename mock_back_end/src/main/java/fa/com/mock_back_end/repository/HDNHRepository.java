package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.HoaDonNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HDNHRepository extends JpaRepository<HoaDonNhapHang, Long> {

    public List<HoaDonNhapHang> findByStatusTrue();
}
