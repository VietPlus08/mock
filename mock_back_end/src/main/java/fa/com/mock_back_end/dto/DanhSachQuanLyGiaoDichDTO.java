package fa.com.mock_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DanhSachQuanLyGiaoDichDTO {
    private Long maHoaDonBanHang;
    private String maNhanVien;
    private Long maKhachHang;
    private LocalDateTime thoiGianBanHang;
    private Long tongHoaDon;
    List<ChiTietHoaDonBanHangDTO> chiTietHoaDonBanHang;
}
