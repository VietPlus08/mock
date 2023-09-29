package fa.com.mock_back_end.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fa.com.mock_back_end.entity.KhachHang;
import fa.com.mock_back_end.entity.NhanVien;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonBanHangDTO {
    private Long maHoaDonBanHang;
    private LocalDateTime thoiGianBanHang;
    private long tongHoaDon;
    private boolean status = true;

    private Map<String, String> errors ; // Khởi tạo hoặc lấy danh sách lỗ

    @JsonIgnore
    private KhachHang khachHang;

    private NhanVien nhanVien;
}
