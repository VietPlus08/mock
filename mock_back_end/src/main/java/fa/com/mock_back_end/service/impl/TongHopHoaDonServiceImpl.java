package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.BanHangDTO;
import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.entity.KhachHang;
import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.service.HDBHService;
import fa.com.mock_back_end.service.TongHopHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TongHopHoaDonServiceImpl implements TongHopHoaDonService {

    @Autowired
    private HDBHService hdbhService;

    @Override
    public HoaDonBanHang themHoaDonBanHang(KhachHang khachHang, BanHangDTO banHangDTO) {
        HoaDonBanHang hoaDonBanHang = new HoaDonBanHang();
        hoaDonBanHang.setKhachHang(khachHang);
        hoaDonBanHang.setNhanVien(new NhanVien(banHangDTO.getMaNhanVien()));
        hoaDonBanHang.setThoiGianBanHang(LocalDateTime.now());
        hoaDonBanHang = hdbhService.save(hoaDonBanHang);
        return hoaDonBanHang;
    }
}
