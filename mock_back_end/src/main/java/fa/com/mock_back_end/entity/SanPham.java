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
    private boolean status;

    @OneToMany(mappedBy = "sanPham")
    List<ChiTietHoaDonNhapHang> listChiTietHoaDonNhapHang;

    @OneToMany(mappedBy = "sanPham")
    List<ChiTietHoaDonNhapHang> listChiTietHoaDonBanHang;
}
