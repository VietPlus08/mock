package fa.com.mock_back_end.dto;

import fa.com.mock_back_end.entity.SanPham;
import lombok.Getter;
import lombok.Setter;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 9/28/2023
*/
@Getter
@Setter
public class ChiTietNhapHangDTO {

    private Long maChiTietHoaDonNhapHang;
    private int soLuong;
    private SanPhamDTO sanPhamDTO;
}
