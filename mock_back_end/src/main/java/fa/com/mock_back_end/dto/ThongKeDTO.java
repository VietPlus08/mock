package fa.com.mock_back_end.dto;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ThongKeDTO {
    // các trường request
    @NotNull
    LocalDate thoiGianBatDau;
    @NotNull
    LocalDate thoiGianKetThuc;
    @Pattern(regexp = "thongKeSanPham|thongKeNhanVien")
    String loaiThongKe;
    // các trường reponse
    long tongSoLuong;
    long tongDoanhThu;
    long tongLoiNhuan;

    // Thống kê theo sản phẩm
    // lấy ChiTietHoaDonBanHang để lấy dữ liệu
    List<SanPhamDTO> listSanPham;

    // Thống kê theo nhân viên
    // lấy HoaDonBanHang để lấy dữ liệu
    List<NhanVienDTO> listNhanVien;

    public void setTongSoLuong(long tongSoLuong) {
        this.tongSoLuong = this.tongSoLuong + tongSoLuong;
    }

    public void setTongDoanhThu(long tongDoanhThu) {
        this.tongDoanhThu = this.tongDoanhThu + tongDoanhThu;
    }

    public void setTongLoiNhuan(long tongLoiNhuan) {
        this.tongLoiNhuan = this.tongLoiNhuan + tongLoiNhuan;
    }
}
