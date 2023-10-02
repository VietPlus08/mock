package fa.com.mock_back_end.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class KhachHangDTO {

    private Long maKhachHang;
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "{HO_TEN}")
    private String tenKhachHang;
    private LocalDate ngaySinh;
    @Pattern(regexp = "Nam|Nu|Khac", message = "{GIOI_TINH}")
    private String gioiTinh;
    @Pattern(regexp = "0[1-9][0-9]{7,8}", message = "{SO_DIEN_THOAI}")
    private String soDienThoai;
}
