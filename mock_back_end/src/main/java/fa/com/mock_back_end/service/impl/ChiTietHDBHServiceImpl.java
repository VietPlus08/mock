package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;
import fa.com.mock_back_end.repository.ChiTietHDBHRepository;
import fa.com.mock_back_end.service.ChiTietHDBHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietHDBHServiceImpl implements ChiTietHDBHService {

    @Autowired
    ChiTietHDBHRepository chiTietHDBHRepository;

    @Override
    public List<ChiTietHoaDonBanHang> findAll() {
        return chiTietHDBHRepository.findByStatusTrue();
    }

    @Override
    public Optional<ChiTietHoaDonBanHang> findById(Long id) {
        return chiTietHDBHRepository.findById(id);
    }

    @Override
    public ChiTietHoaDonBanHang save(ChiTietHoaDonBanHang data) {
        return chiTietHDBHRepository.save(data);
    }

    @Override
    public ChiTietHoaDonBanHang delete(Long id) {
        Optional<ChiTietHoaDonBanHang> chiTietHoaDonBanHang = findById(id);
        if (chiTietHoaDonBanHang.isPresent()) {
            chiTietHoaDonBanHang.get().setStatus(false);
            return chiTietHDBHRepository.save(chiTietHoaDonBanHang.get());
        }
        return null;
    }

    @Override
    public ChiTietHoaDonBanHang update(ChiTietHoaDonBanHang data) {
        Optional<ChiTietHoaDonBanHang> chiTietHoaDonBanHang = findById(data.getMaChiTietHoaDonBanHang());
        if (chiTietHoaDonBanHang.isPresent()) {
            chiTietHoaDonBanHang.get().setSoLuong(data.getSoLuong());
            chiTietHoaDonBanHang.get().setGiaBanThuc(data.getGiaBanThuc());
            chiTietHoaDonBanHang.get().setSanPham(data.getSanPham());
            chiTietHDBHRepository.save(chiTietHoaDonBanHang.get());
        }
        return null;
    }
}
