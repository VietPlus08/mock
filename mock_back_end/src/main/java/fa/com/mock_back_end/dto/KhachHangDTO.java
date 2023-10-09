package fa.com.mock_back_end.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class KhachHangDTO {

    private Long maKhachHang;
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,}$", message = "{REGEX_TEN}")
    private String tenKhachHang;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate ngaySinh;
    @Pattern(regexp = "Nam|Nu|Khac", message = "{REGEX_GIOI_TINH}")
    private String gioiTinh;
    @Pattern(regexp = "^09[0-9]{8}$", message = "{REGEX_SO_DIEN_THOAI}")
    private String soDienThoai;
}
