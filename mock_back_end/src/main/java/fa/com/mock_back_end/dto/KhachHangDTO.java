package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
public class KhachHangDTO {

    private Long maKhachHang;
    private String tenKhachHang;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String soDienThoai;
}
