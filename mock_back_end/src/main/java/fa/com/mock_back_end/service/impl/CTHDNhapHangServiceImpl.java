package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.entity.ChiTietHoaDonNhapHang;
import fa.com.mock_back_end.repository.CTHDNhapHangRepository;
import fa.com.mock_back_end.service.CTHDNhapHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CTHDNhapHangServiceImpl implements CTHDNhapHangService {

    @Autowired
    CTHDNhapHangRepository cthdNhapHangRepository;

    @Override
    public List<ChiTietHoaDonNhapHang> findAll() {
        return cthdNhapHangRepository.findAll();
    }

    @Override
    public Optional<ChiTietHoaDonNhapHang> findById(Long id) {
        return cthdNhapHangRepository.findById(id);
    }

    @Override
    public ChiTietHoaDonNhapHang save(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang) {
        return cthdNhapHangRepository.save(chiTietHoaDonNhapHang);
    }

    @Override
    public ChiTietHoaDonNhapHang update(ChiTietHoaDonNhapHang updateChiTietHoaDonNhapHang) {
        Optional<ChiTietHoaDonNhapHang> chiTietHoaDonNhapHang = findById(updateChiTietHoaDonNhapHang.getMaChiTietHoaDonNhapHang());
        if (chiTietHoaDonNhapHang.isPresent()){
            chiTietHoaDonNhapHang.get().setSoLuong(updateChiTietHoaDonNhapHang.getSoLuong());
            chiTietHoaDonNhapHang.get().setSanPham(updateChiTietHoaDonNhapHang.getSanPham());
            chiTietHoaDonNhapHang.get().setGiaTien(updateChiTietHoaDonNhapHang.getGiaTien());
            return save(chiTietHoaDonNhapHang.get());
        }
        return null;
    }
}
