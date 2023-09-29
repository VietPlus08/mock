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


    @Size(min = 1, max = 50,message = "Ten khach hang phai tu 1 den 50 ky tu")
    @NotBlank(message = "Ten khach hang khong duoc de trong")
    private String tenKhachHang;

    @NotNull(message = "Ngay sinh khong duoc de trong")
    private LocalDate ngaySinh;

    @NotBlank(message = "Gioi tinh khong duoc de trong")
    private String gioiTinh;

    @Pattern(regexp = "^(0|84)[0-9]{9}$", message = "so dien thoai bat dau bang 0 hoac 84, tiep theo la 9 chu so")
    @NotBlank(message = "so dien thoai khong duoc de trong")
    private String soDienThoai;

    @Valid
    private List<TongHopHoaDonDTO> chiTietHoaDonBanHang;

}
