package fa.com.mock_back_end.Converter;

import fa.com.mock_back_end.dto.ChiTietHoaDonBanHangDTO;
import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;
import org.springframework.stereotype.Component;

@Component
public class HoaDonConVerter {


    public ChiTietHoaDonBanHangDTO toDto(ChiTietHoaDonBanHang items) {
        ChiTietHoaDonBanHangDTO chiTietHoaDonBanHangDTO = new ChiTietHoaDonBanHangDTO();
        chiTietHoaDonBanHangDTO.setMaChiTietHoaDonBanHang(items.getMaChiTietHoaDonBanHang());
        chiTietHoaDonBanHangDTO.setMaSanPham(items.getSanPham().getMaSanPham());
        chiTietHoaDonBanHangDTO.setTenSanPham(items.getSanPham().getTenSanPham());
        chiTietHoaDonBanHangDTO.setMaHoaDonBanHang(items.getHoaDonBanHang().getMaHoaDonBanHang());
        chiTietHoaDonBanHangDTO.setSoLuong(items.getSoLuong());
        chiTietHoaDonBanHangDTO.setGiaNiemYetHienTai(items.getGiaNiemYetHienTai());
        chiTietHoaDonBanHangDTO.setGiaBanThuc(items.getGiaBanThuc());
        return chiTietHoaDonBanHangDTO;
    }
}
