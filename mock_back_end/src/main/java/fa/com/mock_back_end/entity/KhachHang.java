package fa.com.mock_back_end.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhacHang;
    @Column(length = 50)
    private String tenKhachHang;
    private LocalDate ngaySinh;
    @Column(length = 10)
    private String gioiTinh;
    @Column(length = 12)
    private String soDienThoai;
    private boolean status;

    @OneToMany(mappedBy = "khachHang")
    List<HoaDonBanHang> listHoaDonBanHang;
}
