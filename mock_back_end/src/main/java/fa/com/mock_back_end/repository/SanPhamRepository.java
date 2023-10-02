package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 10/2/2023
*/
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    List<SanPham> findByStatusTrue();
    SanPham findByStatusTrueAndTenSanPham(String tenSanPham);
}
