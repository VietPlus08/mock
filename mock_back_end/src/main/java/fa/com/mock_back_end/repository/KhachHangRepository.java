package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

    public List<KhachHang> findByStatusTrue();

    KhachHang findByStatusTrueAndSoDienThoai(String soDienThoai);

    KhachHang findBySoDienThoai(String soDienThoai);
}
