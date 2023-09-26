package fa.com.mock_back_end.entity;

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
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSanPham;
    @Column(length = 50)
    private String tenSanPham;
    @Column(length = 50)
    private String nhanHang;
    @Column(length = 10)
    private String boNhoTrong;
    @Column(length = 20)
    private String cpu;
    private long giaVon;
    private long giaNiemYet;
    private int soLuong = 0;
    @Column(length = 20)
    private String mauSac;
    @Column(length = 10)
    private String ram;
    @Column(length = 10)
    private String camera;
    @Column(length = 500)
    private String imageUrl;
    private boolean status = true;

    @OneToMany(mappedBy = "sanPham")
    List<ChiTietHoaDonNhapHang> listChiTietHoaDonNhapHang;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    List<ChiTietHoaDonBanHang> listChiTietHoaDonBanHang;

    public SanPham(Long maSanPham, String tenSanPham, String nhanHang, String boNhoTrong, long giaVon, long giaNiemYet, String mauSac, String ram, String camera, String imageUrl) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.nhanHang = nhanHang;
        this.boNhoTrong = boNhoTrong;
        this.giaVon = giaVon;
        this.giaNiemYet = giaNiemYet;
        this.mauSac = mauSac;
        this.ram = ram;
        this.camera = camera;
        this.imageUrl = imageUrl;
        this.status = true;
    }
}
