package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 9/28/2023
*/
@Getter
@Setter
public class ChiTietHoaDonNhapHangDTO {

    private Long maChiTietHoaDonNhapHang;
    @Min(value = 1, message = "{MIN_SO_LUONG}")
    @Max(value = 1000000, message = "{MAX_SO_LUONG}")
    private int soLuong;
    @NotNull(message = "{NOT_NULL}")
    private SanPhamDTO sanPhamDTO;
}
