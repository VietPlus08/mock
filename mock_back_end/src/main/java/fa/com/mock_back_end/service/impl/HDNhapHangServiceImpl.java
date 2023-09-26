package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.entity.HoaDonNhapHang;
import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.repository.HDNhapHangRepository;
import fa.com.mock_back_end.service.HDNhapHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HDNhapHangServiceImpl implements HDNhapHangService {

    @Autowired
    HDNhapHangRepository hdNhapHangRepository;

    @Override
    public List<HoaDonNhapHang> findAll() {
        return hdNhapHangRepository.findAll();
    }

    @Override
    public Optional<HoaDonNhapHang> findById(Long id) {
        return hdNhapHangRepository.findById(id);
    }

    @Override
    public HoaDonNhapHang save(HoaDonNhapHang hoaDonNhapHang) {
        return hdNhapHangRepository.save(hoaDonNhapHang);
    }

    @Override
    public HoaDonNhapHang update(HoaDonNhapHang hoaDonNhapHang) {
        return null;
    }
}
