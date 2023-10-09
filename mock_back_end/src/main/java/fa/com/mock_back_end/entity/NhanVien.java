package fa.com.mock_back_end.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {

    @Id
    @Column(length = 50)
    private String maNhanVien;

    @Column(columnDefinition = "nvarchar(50)")
    private String tenNhanVien;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate ngaySinh;

    @Column(length = 10)
    private String gioiTinh;

    @Column(length = 10)
    private String chucVu;

    private String matKhau;

    private boolean status = true;

    @OneToMany(mappedBy = "nhanVien")
    List<HoaDonBanHang> listHoaDonBanHang;

    public NhanVien(String maNhanVien, String chucVu, String matKhau) {
        super();
        this.maNhanVien = maNhanVien;
        this.chucVu = chucVu;
        this.matKhau = matKhau;
    }

    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}
