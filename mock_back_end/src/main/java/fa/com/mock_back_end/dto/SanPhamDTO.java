package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "[A-Za-z0-9\\s.]+", message = "{HO_TEN}")
    private String tenSanPham;
    private String nhanHang;
    private String boNhoTrong;
    private String cpu;
    @Min(value = 1, message = "{MIN_GIA_TRI}")
    @Max(value = 999999999, message = "{MAX_GIA_TRI}")
    private long giaVon;
    private long giaNiemYet;
    private String mauSac;
    private String ram;
    private String camera;
    private String imgUrl;
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
