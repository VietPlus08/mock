package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class NhanVienDTO {

    private String maNhanVien;
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,}$", message = "{REGEX_TEN}")
    private String tenNhanVien;
//    @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)", message = "{REGEX_DATE}")
    private LocalDate ngaySinh;
    @Pattern(regexp = "Nam|Nu|Khac", message = "{REGEX_GIOI_TINH}")
    private String gioiTinh;
    private String chucVu;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "{MAT_KHAU}")
    private String matKhau;
    // các trường làm nhiệm vụ thống kê
    private long doanhThu;
    private long loiNhuan;
    private long soLuong;

    public void setDoanhThu(long doanhThu) {
        this.doanhThu += doanhThu;
    }

    public void setLoiNhuan(long loiNhuan) {
        this.loiNhuan += loiNhuan;
    }

    public void setSoLuong(long soLuong) {
        this.soLuong += soLuong;
    }
}
