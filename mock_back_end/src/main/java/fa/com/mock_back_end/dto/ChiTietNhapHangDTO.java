package fa.com.mock_back_end.dto;

import fa.com.mock_back_end.entity.SanPham;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 9/28/2023
*/
@Getter
@Setter
public class ChiTietNhapHangDTO {

    private Long maChiTietHoaDonNhapHang;
    @Min(value = 1, message = "{MIN_SO_LUONG}")
    @Max(value = 999, message = "{MAX_SO_LUONG}")
    private int soLuong;
    @NotNull
    private SanPhamDTO sanPhamDTO;
}
