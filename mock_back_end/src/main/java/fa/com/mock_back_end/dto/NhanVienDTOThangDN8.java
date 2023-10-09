package fa.com.mock_back_end.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Class dùng để nhận form đăng ký từ client gửi tới
 */
public class NhanVienDTOThangDN8 {

    @Length(max = 50, message = "Tối đa 50 ký tự")
    @Pattern(regexp = "^[a-zA-z\\s]+", message = "Chỉ nhập kí tự chữ không dấu")
    private String tenNhanVien;

    @NotNull(message = "Không được để trống")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate ngaySinh;

    private String gioiTinh;

    @NotBlank(message = "Mật khẩu ít nhất 8 kí tự, gồm ít nhất 1 chữ hoa, chữ thường, số và ký tự đặc biệt")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Mật khẩu ít nhất 8 kí tự, gồm ít nhất 1 chữ hoa, chữ thường, số và ký tự đặc biệt")
    private String password;

    @NotBlank(message = "Không được để trống")
    private String rePassword;
}
