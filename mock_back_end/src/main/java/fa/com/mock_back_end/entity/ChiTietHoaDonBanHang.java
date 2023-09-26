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
public class ChiTietHoaDonBanHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietHoaDonBanHang;
    private long giaBanThuc;
    private int soLuong;
    private long giaNiemYetHienTai;
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "maSanPham")
    SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "maHoaDonBanHang")
    HoaDonBanHang hoaDonBanHang;

    @ManyToOne
    @JoinColumn(name = "maSuaChua")
    SuaChua suaChua;
}
