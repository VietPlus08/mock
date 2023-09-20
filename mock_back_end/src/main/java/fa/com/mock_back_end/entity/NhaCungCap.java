package fa.com.mock_back_end.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCap {
    @Id
    private Long maNhaCungCap;
    private String tenNhaCungCap;
    private String email;
    private String soDienThoai;
    private String diaChi;
    private boolean status;


    @OneToMany(mappedBy = "nhaCungCap")
    List<HoaDonNhapHang> listHoaDonNhapHang;
}
