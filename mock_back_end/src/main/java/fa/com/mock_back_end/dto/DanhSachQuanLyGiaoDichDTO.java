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
    private String tenKhachHang;
    private Long maKhachHang;
    private LocalDateTime thoiGianBanHang;
    private Long tongHoaDon;
    List<ChiTietHoaDonBanHangDTO> chiTietHoaDonBanHang;

    public <U> DanhSachQuanLyGiaoDichDTO(Object maChiTietHoaDonBanHang, U u, U u1, U u2, Object soLuong, Object giaNiemYetHienTai, Object giaBanThuc) {
    }

    public DanhSachQuanLyGiaoDichDTO(Long maHoaDonBanHang, String s, Long aLong, LocalDateTime thoiGianBanHang) {
    }
}
