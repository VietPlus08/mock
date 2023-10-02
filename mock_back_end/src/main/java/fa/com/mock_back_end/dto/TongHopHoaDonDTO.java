package fa.com.mock_back_end.dto;

import lombok.*;

import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TongHopHoaDonDTO {


    @NotNull(message = "Gia ban thuc khong duoc trong")
    @Min(value = 1, message = "Gia ban thuc phai lon hon 0")
    private long giaBanThuc;

    @NotNull(message = "So luong khong duoc bo trong")
    @Min(value = 1, message = "So luong phai lon hon 0")
    private int soLuong;


    private long giaNiemYetHienTai;

    @NotNull(message = "Ma san pham khong duoc bo trong")
    private Long maSanPham;
}
