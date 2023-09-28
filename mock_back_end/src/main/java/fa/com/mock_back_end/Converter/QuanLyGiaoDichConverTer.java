package fa.com.mock_back_end.Converter;

import fa.com.mock_back_end.dto.ChiTietHoaDonBanHangDTO;
import fa.com.mock_back_end.dto.DanhSachQuanLyGiaoDichDTO;
import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;
import fa.com.mock_back_end.entity.HoaDonBanHang;
import org.springframework.stereotype.Component;

@Component
public class QuanLyGiaoDichConverTer {

    public DanhSachQuanLyGiaoDichDTO toQuanLyGiaoDichDto(HoaDonBanHang items) {
        DanhSachQuanLyGiaoDichDTO danhSachQuanLyGiaoDichDTO = new DanhSachQuanLyGiaoDichDTO();
        danhSachQuanLyGiaoDichDTO.setMaHoaDonBanHang(items.getMaHoaDonBanHang());
        //danhSachQuanLyGiaoDichDTO.setMaNhanVien(items.getNhanVien().getMaNhanVien());
        danhSachQuanLyGiaoDichDTO.setMaKhachHang(items.getKhachHang().getMaKhachHang());
        danhSachQuanLyGiaoDichDTO.setThoiGianBanHang(items.getThoiGianBanHang());
        return danhSachQuanLyGiaoDichDTO;
    }
}
