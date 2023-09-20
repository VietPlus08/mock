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
    private String tenKhachHang;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String soDienThoai;
    private boolean status;

    @OneToMany(mappedBy = "khachHang")
    List<HoaDonBanHang> listHoaDonBanHang;
}
