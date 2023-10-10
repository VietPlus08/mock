package fa.com.mock_back_end.dto;

import lombok.*;

import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TongHopHoaDonDTO {


    @NotNull(message = "{NOT_NULL}")
    @Min(value = 1, message = "{MIN_GIA_TRI}")
    @Max(value = 1000000000, message = "{MAX_GIA_TRI}")
    private long giaBanThuc;


    @NotNull(message = "{NOT_NULL}")
    @Min(value = 1, message = "{MIN_SO_LUONG}")
    @Max(value = 1000000, message = "{MAX_SO_LUONG}")
    private int soLuong;

    private long giaNiemYetHienTai;

    @NotNull(message = "{NOT_NULL}")
    private Long maSanPham;
}
