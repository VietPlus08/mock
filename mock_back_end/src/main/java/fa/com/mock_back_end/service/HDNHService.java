package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.ChiTietNhapHangDTO;
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
    HoaDonNhapHang delete(Long id);
    NhapHangDTO update(NhapHangDTO item);
    long getTongTien(List<ChiTietNhapHangDTO> listChiTietNhapHangDTO);
    public List<ChiTietHoaDonNhapHang> getListChiTietHoaDonNhapHang(List<ChiTietNhapHangDTO> listChiTietNhapHangDTO,
                                                                    long maHoaDonNhapHang);
    public ChiTietHoaDonNhapHang getChiTietHoaDonNhapHang(ChiTietNhapHangDTO chiTietNhapHangDTO,
                                                          long maHoaDonNhapHang);
    public NhapHangDTO getNhapHangDTO(HoaDonNhapHang nhapHang);
    public List<ChiTietNhapHangDTO> getListChiTietNhapHangDTO(List<ChiTietHoaDonNhapHang> data);
    public ChiTietNhapHangDTO getChiTietNhapHangDTO(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang);
    public SanPhamDTO getSanPhamDTO(SanPham sanPham);
}
