package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.HoaDonNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HDNHRepository extends JpaRepository<HoaDonNhapHang, Long> {

    public List<HoaDonNhapHang> findByStatusTrue();

    @Query(value = "insert into HoaDonNhapHang (maNhaCungCap, tongHoaDon, status) values (?1, ?2, ?3)", nativeQuery = true)
    public void saveByUser(long maNCC, long tong, boolean status);
}
