package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
* @Author: QUANGNA7
* @Version: 1.0
* @Since: 9/28/2023
* @Description:
*/
@Getter
@Setter
public class SanPhamDTO {

    private Long maSanPham;
    private String tenSanPham;
    private String nhanHang;
    private String boNhoTrong;
    private String cpu;
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
