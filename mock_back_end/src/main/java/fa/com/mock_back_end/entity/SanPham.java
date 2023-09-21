package fa.com.mock_back_end.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    private String tenSanPham;
    private String nhanHang;
    private String boNhoTrong;
    private long giaVon;
    private long giaNiemYet;
    private String mauSac;
    private String ram;
    private String camera;
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
