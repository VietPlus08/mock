package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class NhaCungCapDTO {

    private Long maNhaCungCap;
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "{HO_TEN}")
    private String tenNhaCungCap;
    @Email(message = "{EMAIL}")
    private String email;
    @Pattern(regexp = "0[1-9][0-9]{7,8}", message = "{SO_DIEN_THOAI}")
    private String soDienThoai;
    @Pattern(regexp = "[A-Za-z0-9.\\s]+", message = "{DIA_CHI}")
    private String diaChi;
}
