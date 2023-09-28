package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.entity.ChiTietHoaDonNhapHang;
import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.repository.ChiTietHDNHRepository;
import fa.com.mock_back_end.service.ChiTietHDNHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietHDNHServiceImpl implements ChiTietHDNHService {

    @Autowired
    ChiTietHDNHRepository chiTietHDNHRepository;

    @Override
    public List<ChiTietHoaDonNhapHang> findAll() {
        return chiTietHDNHRepository.findByStatusTrue();
    }

    @Override
    public Optional<ChiTietHoaDonNhapHang> findById(Long id) {
        return chiTietHDNHRepository.findById(id);
    }

    @Override
    public ChiTietHoaDonNhapHang save(ChiTietHoaDonNhapHang data) {
        return chiTietHDNHRepository.save(data);
    }

    public void saveAll(List<ChiTietHoaDonNhapHang> listData){
        chiTietHDNHRepository.saveAll(listData);
    }

    @Override
    public ChiTietHoaDonNhapHang delete(Long id) {
        Optional<ChiTietHoaDonNhapHang> chiTietHoaDonNhapHang = findById(id);
        if (chiTietHoaDonNhapHang.isPresent()) {
            chiTietHoaDonNhapHang.get().setStatus(false);
            return chiTietHDNHRepository.save(chiTietHoaDonNhapHang.get());
        }
        return null;
    }

    @Override
    public ChiTietHoaDonNhapHang update(ChiTietHoaDonNhapHang data) {
        Optional<ChiTietHoaDonNhapHang> chiTietHoaDonNhapHang = findById(data.getMaChiTietHoaDonNhapHang());
        if (chiTietHoaDonNhapHang.isPresent()) {
            chiTietHoaDonNhapHang.get().setSoLuong(data.getSoLuong());
            chiTietHoaDonNhapHang.get().setSanPham(data.getSanPham());
            chiTietHDNHRepository.save(chiTietHoaDonNhapHang.get());
        }
            return null;
    }
}
