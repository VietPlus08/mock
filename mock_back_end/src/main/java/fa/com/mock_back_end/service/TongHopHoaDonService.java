package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.BanHangDTO;
import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.entity.KhachHang;

public interface TongHopHoaDonService {
    HoaDonBanHang themHoaDonBanHang(KhachHang khachHang, BanHangDTO banHangDTO);
}
