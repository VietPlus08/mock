package fa.com.mock_back_end.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {
    @Id
    @Column(length = 10)
    private String maNhanVien;
    @Column(length = 50)
    private String tenNhanVien;
    private LocalDate ngaySinh;
    @Column(length = 10)
    private String gioiTinh;
    @Column(length = 10)
    private String chucVu;
    private String matKhau;
    private boolean status = true;

    @OneToMany(mappedBy = "nhanVien")
    List<HoaDonBanHang> listHoaDonBanHang;
}
