package fa.com.mock_back_end.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDonNhapHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietHoaDonNhapHang;
    private int soLuong;
    private long giaTien;
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "maSanPham")
    SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "maHoaDonNhapHang")
    HoaDonNhapHang hoaDonNhapHang;
}
