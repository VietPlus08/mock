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
public class HoaDonNhapHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHoaDonNhapHang;
    private LocalDateTime thoiGianNhapHang;
    private Long tongHoaDon;

    @ManyToOne
    @JoinColumn(name = "maNhaCungCap")
    NhaCungCap nhaCungCap;

    @OneToMany(mappedBy = "hoaDonNhapHang", fetch = FetchType.EAGER)
    List<ChiTietHoaDonNhapHang> listChiTietHoaDonNhapHang;
}
