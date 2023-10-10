package fa.com.mock_back_end.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNhaCungCap;
    @Column(columnDefinition = "nvarchar(50)")
    private String tenNhaCungCap;
    @Column(length = 100)
    private String email;
    @Column(length = 12)
    private String soDienThoai;
    @Column(length = 100)
    private String diaChi;
    private boolean status = true;

    @OneToMany(mappedBy = "nhaCungCap")
    List<HoaDonNhapHang> listHoaDonNhapHang;

    public NhaCungCap(Long maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }
}
