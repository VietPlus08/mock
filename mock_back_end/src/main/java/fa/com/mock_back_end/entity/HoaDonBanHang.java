package fa.com.mock_back_end.entity;

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
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonBanHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHoaDonBanHang;
    private LocalDateTime thoiGianBanHang;
    private long tongHoaDon;
    private String loaiHoaDon;

    @ManyToOne
    @JoinColumn(name = "maKhangHang")
    KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "maNhanVien")
    NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDonBanHang")
    List<ChiTietHoaDonBanHang> listChiTietHoaDonBanHang;

}
