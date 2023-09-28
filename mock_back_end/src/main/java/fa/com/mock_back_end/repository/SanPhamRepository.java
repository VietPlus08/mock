package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    List<SanPham> findByStatusTrue();
    SanPham findByStatusTrueAndTenSanPham(String tenSanPham);

}
