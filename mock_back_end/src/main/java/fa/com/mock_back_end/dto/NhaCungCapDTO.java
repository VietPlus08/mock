package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class NhaCungCapDTO {

    private Long maNhaCungCap;
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,}$", message = "{REGEX_TEN}")
    private String tenNhaCungCap;
    @NotBlank(message = "{NOT_BLANK}")
    @Email(message = "{EMAIL}")
    private String email;
//    @Pattern(regexp = "^09[0-9]{8}$", message = "{REGEX_SO_DIEN_THOAI}")
    private String soDienThoai;
    @Pattern(regexp = "^[a-zA-Z0-9/ ]{1,}$", message = "{REGEX_DIA_CHI}")
    private String diaChi;
}