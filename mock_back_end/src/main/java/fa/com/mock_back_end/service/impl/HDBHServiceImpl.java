package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.entity.HoaDonNhapHang;
import fa.com.mock_back_end.repository.HDBHRepository;
import fa.com.mock_back_end.service.HDBHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HDBHServiceImpl implements HDBHService {

    @Autowired
    HDBHRepository hdbhRepository;

    @Override
    public List<HoaDonBanHang> findAll() {
        return hdbhRepository.findByStatusTrue();
    }

    @Override
    public Optional<HoaDonBanHang> findById(Long id) {
        return hdbhRepository.findById(id);
    }

    @Override
    public HoaDonBanHang save(HoaDonBanHang data) {
        return hdbhRepository.save(data);
    }

    @Override
    public HoaDonBanHang delete(Long id) {
        Optional<HoaDonBanHang> hoaDonBanHang = findById(id);
        if (hoaDonBanHang.isPresent()) {
            hoaDonBanHang.get().setStatus(false);
            return hdbhRepository.save(hoaDonBanHang.get());
        }
        return null;
    }

    @Override
    public HoaDonBanHang update(HoaDonBanHang data) {
        Optional<HoaDonBanHang> hoaDonBanHang = findById(data.getMaHoaDonBanHang());
        if (hoaDonBanHang.isPresent()){
            hoaDonBanHang.get().setTongHoaDon(data.getTongHoaDon());
            hoaDonBanHang.get().setKhachHang(data.getKhachHang());
            hoaDonBanHang.get().setNhanVien(data.getNhanVien());
            return hdbhRepository.save(hoaDonBanHang.get());
        }
        return null;    }
}
