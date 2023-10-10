package fa.com.mock_back_end.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fa.com.mock_back_end.entity.NhanVien;

import javax.transaction.Transactional;

public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    @Query("select n.maNhanVien from NhanVien n")
    List<String> getByMaNhanVien();

    List<NhanVien> findByStatusTrue();

    @Query(value = "SELECT ma_nhan_vien FROM nhan_vien WHERE ma_nhan_vien LIKE ?1% and status = 1;", nativeQuery = true)
    List<String> getListMaNhanVien(String maNhanVien);

    @Transactional
    @Modifying
    @Query(value = "update nhan_vien set ten_nhan_vien =?1, ngay_sinh=?2, gioi_tinh=?3 where ma_nhan_vien = ?4", nativeQuery = true)
    int updateNhanVienInfor(String ten, LocalDate ngaySinh, String gioiTinh, String maNhanVien);

    @Transactional
    @Modifying
    @Query(value = "update nhan_vien set mat_khau =?1 where ma_nhan_vien = ?2", nativeQuery = true)
    int updateNhanVienPassword(String password, String maNhanVien);
}
