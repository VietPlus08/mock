package fa.com.mock_back_end.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class NhaCungCapDTO {

    private Long maNhaCungCap;
    private String tenNhaCungCap;
    private String email;
    private String soDienThoai;
    private String diaChi;
}
