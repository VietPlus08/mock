package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class NhanVienDTO {

    private String maNhanVien;
    @Pattern(regexp = "[0-9\\p{L}_\\s]+", message = "{REGEX_TEN}")
    private String tenNhanVien;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate ngaySinh;
    private String gioiTinh;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "{MAT_KHAU}")
    private String password;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "{MAT_KHAU}")
    private String rePassword;
    // các trường làm nhiệm vụ thống kê
    private long doanhThu;
    private long loiNhuan;
    private long soLuongBan;

    public void setDoanhThu(long doanhThu) {
        this.doanhThu += doanhThu;
    }

    public void setLoiNhuan(long loiNhuan) {
        this.loiNhuan += loiNhuan;
    }

    public void setSoLuongBan(long soLuongBan) {
        this.soLuongBan += soLuongBan;
    }
}
