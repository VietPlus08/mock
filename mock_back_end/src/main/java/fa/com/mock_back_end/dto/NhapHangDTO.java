package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "{NOT_NULL}")
    private long maNhaCungCap;
    private String tenNhaCungCap;
    private LocalDateTime thoiGianNhapHang;
    private long tongHoaDon;
    @Valid
    @NotNull(message = "{NOT_NULL}")
    List<ChiTietHoaDonNhapHangDTO> chiTietHoaDonNhapHangDTO;
}
