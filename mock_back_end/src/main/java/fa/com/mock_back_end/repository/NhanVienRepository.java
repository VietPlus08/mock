package fa.com.mock_back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fa.com.mock_back_end.entity.NhanVien;

public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    @Query("select n.maNhanVien from NhanVien n")
    List<String> getByMaNhanVien();

    List<NhanVien> findByStatusTrue();

    @Query(value = "SELECT ma_nhan_vien FROM nhan_vien WHERE ma_nhan_vien LIKE ?1%;", nativeQuery = true)
    List<String> getListMaNhanVien(String maNhanVien);
}
