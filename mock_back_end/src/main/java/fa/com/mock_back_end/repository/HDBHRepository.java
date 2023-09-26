package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.HoaDonBanHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HDBHRepository extends JpaRepository<HoaDonBanHang, Long> {

    public List<HoaDonBanHang> findByStatusTrue();
}
