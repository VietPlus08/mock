package fa.com.mock_back_end.Converter;

import fa.com.mock_back_end.dto.ChiTietHoaDonBanHangDTO;
import fa.com.mock_back_end.dto.DanhSachQuanLyGiaoDichDTO;
import fa.com.mock_back_end.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuanLyGiaoDichConverTer {

    @Autowired
    ModelMapper modelMapper;

    public DanhSachQuanLyGiaoDichDTO toQuanLyGiaoDichDto(HoaDonBanHang items) {
        DanhSachQuanLyGiaoDichDTO danhSachQuanLyGiaoDichDTO = modelMapper.map(items, DanhSachQuanLyGiaoDichDTO.class);
        Optional<NhanVien> nhanVien = Optional.ofNullable(items.getNhanVien());
        Optional<KhachHang> khachHang = Optional.ofNullable(items.getKhachHang());
        if (nhanVien.isPresent()) {
            danhSachQuanLyGiaoDichDTO.setMaNhanVien(items.getNhanVien().getMaNhanVien());
        }
        if (khachHang.isPresent()) {
            danhSachQuanLyGiaoDichDTO.setMaKhachHang(items.getKhachHang().getMaKhachHang());
        }
        return danhSachQuanLyGiaoDichDTO;
    }
}

