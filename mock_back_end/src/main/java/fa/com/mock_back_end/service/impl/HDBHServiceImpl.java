package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.TongHopHoaDonDTO;
import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;
import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.entity.HoaDonNhapHang;
import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.repository.HDBHRepository;
import fa.com.mock_back_end.service.ChiTietHDBHService;
import fa.com.mock_back_end.service.HDBHService;
import fa.com.mock_back_end.service.SanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HDBHServiceImpl implements HDBHService {

    @Autowired
    HDBHRepository hdbhRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private ChiTietHDBHService chiTietHDBHService;



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

    /**
     * @Author Nguyễn Xuân Long
     * @Decription: thêm chi tiết hóa đơn bán hàng vào database
     * @return  1
     */

    @Override
    public int themChiTietHoaDonBanHang(List<TongHopHoaDonDTO> listChiTietHoaDon, HoaDonBanHang hoaDonBanHang) {
        for (TongHopHoaDonDTO items : listChiTietHoaDon) {
            ChiTietHoaDonBanHang chiTietHoaDonBanHang = modelMapper.map(items, ChiTietHoaDonBanHang.class);

            SanPham sanPham = sanPhamService.findById(items.getMaSanPham()).orElse(null);

            chiTietHoaDonBanHang.setSanPham(sanPham);
            chiTietHoaDonBanHang.setHoaDonBanHang(hoaDonBanHang);
            chiTietHoaDonBanHang = chiTietHDBHService.save(chiTietHoaDonBanHang);
        }
        return 1;
    }
}
