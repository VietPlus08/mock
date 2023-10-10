package fa.com.mock_back_end.dto;

import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.entity.KhachHang;
import fa.com.mock_back_end.entity.SanPham;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BanHangDTO {

    @NotBlank(message = "{REGEX_TEN}")
    @Pattern(regexp = "[0-9\\p{L}_\\s]+", message = "{REGEX_TEN}")
    private String tenKhachHang;


//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotNull(message = "Ngay sinh khong duoc de trong")
    private LocalDate ngaySinh;

    private String maNhanVien;

    @Pattern(regexp = "Nam|Nu|Khac", message = "{REGEX_GIOI_TINH}")
    private String gioiTinh;

    @Pattern(regexp = "^09[0-9]{8}$", message = "{REGEX_SO_DIEN_THOAI}")
    private String soDienThoai;

    @Valid
    private List<TongHopHoaDonDTO> chiTietHoaDonBanHang;

}
