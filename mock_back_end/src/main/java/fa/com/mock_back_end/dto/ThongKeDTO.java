package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ThongKeDTO {
    // các trường request

    @NotNull(message = "{NOT_NULL}")
    @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)", message = "{REGEX_DATE}")
    LocalDate thoiGianBatDau;
    @NotNull(message = "{NOT_NULL}")
    @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)", message = "{REGEX_DATE}")
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
