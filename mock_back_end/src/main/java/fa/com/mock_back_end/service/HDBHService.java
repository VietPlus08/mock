package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.TongHopHoaDonDTO;
import fa.com.mock_back_end.entity.HoaDonBanHang;

import java.util.List;
import java.util.Optional;

public interface HDBHService {

    List<HoaDonBanHang> findAll();
    Optional<HoaDonBanHang> findById(Long id);
    HoaDonBanHang save(HoaDonBanHang item);
    HoaDonBanHang delete(Long id);
    HoaDonBanHang update(HoaDonBanHang item);

    int themChiTietHoaDonBanHang(List<TongHopHoaDonDTO> listChiTietHoaDon, HoaDonBanHang hoaDonBanHang);
}
