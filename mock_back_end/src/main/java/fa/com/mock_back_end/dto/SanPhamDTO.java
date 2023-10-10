package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;

/**
* @Author: QUANGNA7
* @Version: 1.0
* @Since: 9/28/2023
* @Description:
*/
@Getter
@Setter
public class SanPhamDTO {

    private long maSanPham;
    @NotBlank(message = "{NOT_BLANK}")
    @Pattern(regexp = "[0-9\\p{L}_\\s]+", message = "{REGEX_TEN}")
    private String tenSanPham;
    @NotBlank(message = "{NOT_BLANK}")
    @Pattern(regexp = "[a-zA-Z0-9\\s]+", message = "{REGEX_NHAN_HANG}")
    private String nhanHang;
    @NotBlank(message = "{NOT_BLANK}")
    @Pattern(regexp = "[1-9]([0-9]+)GB", message = "{REGEX_BO_NHO}")
    private String boNhoTrong;
    @NotBlank(message = "{NOT_BLANK}")
    @Pattern(regexp = "[a-zA-Z0-9\\s]+", message = "{REGEX_CPU}")
    private String cpu;
    @Min(value = 1, message = "{MIN_GIA_TRI}")
    @Max(value = 1000000000, message = "{MAX_GIA_TRI}")
    private long giaVon;
    @Min(value = 1, message = "{MIN_GIA_TRI}")
    @Max(value = 1000000000, message = "{MAX_GIA_TRI}")
    private long giaNiemYet;
    @Pattern(regexp = "[a-zA-Z0-9\\s]+", message = "{REGEX_MAU_SAC}")
    private String mauSac;
    @NotBlank(message = "{NOT_BLANK}")
    private String ram;
    @NotBlank(message = "{NOT_BLANK}")
    @Pattern(regexp = "[1-9]([0-9]+)MP", message = "{REGEX_CAMERA}")
    private String camera;
    @NotBlank(message = "{NOT_BLANK}")
    private String imgUrl;
    private long soLuong;
    // các trường làm nhiệm vụ thống kê
    private long doanhThu;
    private long loiNhuan;

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
