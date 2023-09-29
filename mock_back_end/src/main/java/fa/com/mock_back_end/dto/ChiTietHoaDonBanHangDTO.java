package fa.com.mock_back_end.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDonBanHangDTO {


    private Long maChiTietHoaDonBanHang;
    private Long maSuaChua;
    private Long maSanPham;
    private String tenSanPham;
    private Long maHoaDonBanHang;

    private int soLuong;
    private long giaNiemYetHienTai;
    private long giaBanThuc;


    public ChiTietHoaDonBanHangDTO(Long maChiTietHoaDonBanHang, Object o, Object o1, Object o2, int soLuong, long giaNiemYetHienTai, long giaBanThuc) {
    }
}


