package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.entity.ChiTietHoaDonNhapHang;
import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.repository.ChiTietHDNHRepository;
import fa.com.mock_back_end.service.ChiTietHDNHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 10/2/2023
*/
@Service
public class ChiTietHDNHServiceImpl implements ChiTietHDNHService {

    @Autowired
    ChiTietHDNHRepository chiTietHDNHRepository;

    /**
    * @Description findAll
    * @Param
    * @Return List<ChiTietHoaDonNhapHang>
    */
    @Override
    public List<ChiTietHoaDonNhapHang> findAll() {
        return chiTietHDNHRepository.findByStatusTrue();
    }

    /**
    * @Description findById
    * @Param id
    * @Return Optional<ChiTietHoaDonNhapHang>
    */
    @Override
    public Optional<ChiTietHoaDonNhapHang> findById(Long id) {
        return chiTietHDNHRepository.findById(id);
    }

    /**
    * @Description save
    * @Param data
    * @Return ChiTietHoaDonNhapHang
    */
    @Override
    public ChiTietHoaDonNhapHang save(ChiTietHoaDonNhapHang data) {
        return chiTietHDNHRepository.save(data);
    }

    /**
    * @Description saveAll
    * @Param listData
    * @Return
    */
    public void saveAll(List<ChiTietHoaDonNhapHang> listData) {
        chiTietHDNHRepository.saveAll(listData);
    }

    /**
    * @Description delete
    * @Param id
    * @Return ChiTietHoaDonNhapHang
    */
    @Override
    public ChiTietHoaDonNhapHang delete(Long id) {
        Optional<ChiTietHoaDonNhapHang> chiTietHoaDonNhapHang = findById(id);
        if (chiTietHoaDonNhapHang.isPresent()) {
            chiTietHoaDonNhapHang.get().setStatus(false);
            return chiTietHDNHRepository.save(chiTietHoaDonNhapHang.get());
        }
        return null;
    }

    /**
    * @Description update
    * @Param data
    * @Return ChiTietHoaDonNhapHang
    */
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
