package fa.com.mock_back_end.dto;

import fa.com.mock_back_end.entity.ChiTietHoaDonNhapHang;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
* @Author: QUANGNA7
* @Version: 1.0
* @Since: 9/28/2023
* @Description:
*/
@Getter
@Setter
public class NhapHangDTO {

    private long maHoaDonNhapHang;
    private long maNhaCungCap;
    private String tenNhaCungCap;
    private LocalDateTime thoiGianNhapHang;
    private long tongHoaDon;
    List<ChiTietNhapHangDTO> chiTietHoaDonNhapHangDTO;
}
