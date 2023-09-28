package fa.com.mock_back_end.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class HoaDonNhapHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHoaDonNhapHang;
    private LocalDateTime thoiGianNhapHang;
    private Long tongHoaDon;
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "maNhaCungCap")
    NhaCungCap nhaCungCap;

    @OneToMany(mappedBy = "hoaDonNhapHang", fetch = FetchType.EAGER)
    List<ChiTietHoaDonNhapHang> listChiTietHoaDonNhapHang;

    public HoaDonNhapHang() {
        this.thoiGianNhapHang = LocalDateTime.now();
    }

    public HoaDonNhapHang(Long maHoaDonNhapHang){
        this.maHoaDonNhapHang = maHoaDonNhapHang;
    }
}
