package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.entity.HoaDonNhapHang;
import fa.com.mock_back_end.repository.HDNHRepository;
import fa.com.mock_back_end.service.HDNHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HDNHServiceImpl implements HDNHService {

    @Autowired
    HDNHRepository hdnhRepository;

    @Override
    public List<HoaDonNhapHang> findAll() {
        return hdnhRepository.findByStatusTrue();
    }

    @Override
    public Optional<HoaDonNhapHang> findById(Long id) {
        return hdnhRepository.findById(id);
    }

    @Override
    public HoaDonNhapHang save(HoaDonNhapHang data) {
        return hdnhRepository.save(data);
    }

    @Override
    public HoaDonNhapHang delete(Long id) {
        Optional<HoaDonNhapHang> hoaDonNhapHang = findById(id);
        if (hoaDonNhapHang.isPresent()) {
            hoaDonNhapHang.get().setStatus(false);
            return hdnhRepository.save(hoaDonNhapHang.get());
        }
        return null;
    }

    @Override
    public HoaDonNhapHang update(HoaDonNhapHang data) {
        Optional<HoaDonNhapHang> hoaDonNhapHang = findById(data.getMaHoaDonNhapHang());
        if (hoaDonNhapHang.isPresent()){
            hoaDonNhapHang.get().setTongHoaDon(data.getTongHoaDon());
            hoaDonNhapHang.get().setNhaCungCap(data.getNhaCungCap());
            return hdnhRepository.save(hoaDonNhapHang.get());
        }
        return null;
    }
}
