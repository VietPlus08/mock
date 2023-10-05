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
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,}$", message = "{REGEX_TEN}")
    private String tenKhachHang;
//    @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)", message = "{REGEX_DATE}")
    private LocalDate ngaySinh;
    @Pattern(regexp = "Nam|Nu|Khac", message = "{REGEX_GIOI_TINH}")
    private String gioiTinh;
    @Pattern(regexp = "^09[0-9]{8}$", message = "{REGEX_SO_DIEN_THOAI}")
    private String soDienThoai;
}
