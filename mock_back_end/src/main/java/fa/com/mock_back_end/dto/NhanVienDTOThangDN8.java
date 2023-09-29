package fa.com.mock_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienDTOThangDN8 {

    @Length(max = 50, message = "Không đúng format")
    @Pattern(regexp = "^[a-zA-Z\\s]+", message = "Chỉ nhập kí tự chữ")
    private String tenNhanVien;

    @NotBlank(message = "Không được để trống")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate ngaySinh;

    private String gioiTinh;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Mật khẩu ít nhất 8 kí tự, gồm ít nhất 1 chữ hoa, chữ thường, số và ký tự đặc biệt")
    private String matKhau;

    private String reMatKhau;
}
