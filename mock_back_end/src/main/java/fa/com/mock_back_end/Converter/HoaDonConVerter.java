package fa.com.mock_back_end.Converter;

import fa.com.mock_back_end.dto.ChiTietHoaDonBanHangDTO;
import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;
import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.entity.HoaDonBanHang;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.Optional;

@Component
public class HoaDonConVerter {

    @Autowired
    ModelMapper modelMapper;


    public ChiTietHoaDonBanHangDTO toDto(ChiTietHoaDonBanHang chiTietHoaDonBanHang) {
        ChiTietHoaDonBanHangDTO chiTietHoaDonBanHangDTO = modelMapper.map(chiTietHoaDonBanHang, ChiTietHoaDonBanHangDTO.class);
        Optional<SanPham> sanPham = Optional.ofNullable(chiTietHoaDonBanHang.getSanPham());
        if (sanPham.isPresent()) {
            chiTietHoaDonBanHangDTO.setMaSanPham(chiTietHoaDonBanHang.getSanPham().getMaSanPham());
            chiTietHoaDonBanHangDTO.setTenSanPham(chiTietHoaDonBanHang.getSanPham().getTenSanPham());
        }
        Optional<HoaDonBanHang> hoaDonBanHang = Optional.ofNullable(chiTietHoaDonBanHang.getHoaDonBanHang());
        if (hoaDonBanHang.isPresent()) {
            chiTietHoaDonBanHangDTO.setMaHoaDonBanHang(chiTietHoaDonBanHang.getHoaDonBanHang().getMaHoaDonBanHang());
        }

        return chiTietHoaDonBanHangDTO;

    }
}
