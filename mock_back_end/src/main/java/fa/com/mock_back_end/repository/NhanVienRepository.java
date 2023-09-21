package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    @Query("select n.maNhanVien from NhanVien n")
    List<String> getByMaNhanVien();
    List<NhanVien> findByStatusTrue();
}
