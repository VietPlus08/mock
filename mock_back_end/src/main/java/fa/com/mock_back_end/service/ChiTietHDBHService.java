package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.BanHangDTO;
import fa.com.mock_back_end.dto.TongHopHoaDonDTO;
import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;
import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.entity.KhachHang;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ChiTietHDBHService {

    List<ChiTietHoaDonBanHang> findAll();

    Optional<ChiTietHoaDonBanHang> findById(Long id);

    ChiTietHoaDonBanHang save(ChiTietHoaDonBanHang item);

    ChiTietHoaDonBanHang delete(Long id);

    ChiTietHoaDonBanHang update(ChiTietHoaDonBanHang item);

    Map<Long, Integer> themHoaDonVaoMap(List<TongHopHoaDonDTO> listChiTietHoaDon);

    int thayDoiSoLuongSanPham(List<TongHopHoaDonDTO> listChiTietHoaDon);

    KhachHang themKhachHangMoi(BanHangDTO banHangDTO);

    Map<String, String> checkSoLuongTrongKho(Map<Long, Integer> danhSachTong, List<TongHopHoaDonDTO> listChiTietHoaDon);

    long tongHoaDon(List<ChiTietHoaDonBanHang> chiTietHoaDonBanHangList);

}
