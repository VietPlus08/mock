package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
}
