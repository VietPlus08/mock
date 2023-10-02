package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.HoaDonNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 10/2/2023
*/
public interface HDNHRepository extends JpaRepository<HoaDonNhapHang, Long> {

    public List<HoaDonNhapHang> findByStatusTrue();
}
