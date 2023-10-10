package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.ChiTietHoaDonNhapHangDTO;
import fa.com.mock_back_end.dto.NhapHangDTO;
import fa.com.mock_back_end.dto.SanPhamDTO;
import fa.com.mock_back_end.entity.ChiTietHoaDonNhapHang;
import fa.com.mock_back_end.entity.HoaDonNhapHang;
import fa.com.mock_back_end.entity.SanPham;

import java.util.List;
import java.util.Optional;

public interface HDNHService {

    List<NhapHangDTO> findAllDTO();
    List<HoaDonNhapHang> findAll();
    Optional<HoaDonNhapHang> findById(Long id);
    NhapHangDTO save(NhapHangDTO item);
    NhapHangDTO update(NhapHangDTO item);
    long getTongTien(List<ChiTietHoaDonNhapHangDTO> listChiTietHoaDonNhapHangDTO);
    public List<ChiTietHoaDonNhapHang> getListChiTietHoaDonNhapHang(List<ChiTietHoaDonNhapHangDTO> listChiTietHoaDonNhapHangDTO,
                                                                    long maHoaDonNhapHang);
    public ChiTietHoaDonNhapHang getChiTietHoaDonNhapHang(ChiTietHoaDonNhapHangDTO chiTietHoaDonNhapHangDTO,
                                                          long maHoaDonNhapHang);
    public NhapHangDTO getNhapHangDTO(HoaDonNhapHang nhapHang);
    public List<ChiTietHoaDonNhapHangDTO> getListChiTietNhapHangDTO(List<ChiTietHoaDonNhapHang> data);
    public ChiTietHoaDonNhapHangDTO getChiTietNhapHangDTO(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang);
    public SanPhamDTO getSanPhamDTO(SanPham sanPham);
}
