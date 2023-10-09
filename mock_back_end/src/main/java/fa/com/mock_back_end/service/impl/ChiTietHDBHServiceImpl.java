package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.BanHangDTO;
import fa.com.mock_back_end.dto.TongHopHoaDonDTO;
import fa.com.mock_back_end.entity.*;
import fa.com.mock_back_end.repository.ChiTietHDBHRepository;
import fa.com.mock_back_end.service.ChiTietHDBHService;
import fa.com.mock_back_end.service.HDBHService;
import fa.com.mock_back_end.service.KhachHangService;
import fa.com.mock_back_end.service.SanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ChiTietHDBHServiceImpl implements ChiTietHDBHService {

    @Autowired
    ChiTietHDBHRepository chiTietHDBHRepository;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    ModelMapper modelMapper;

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

    /**
     * @Author Nguyễn Xuân Long
     * @Decription: thêm số lượng của những sản phẩm giống nhau vào map
     * @return Map<Long, Integer> danhSachTong
     */
    @Override
    public Map<Long, Integer> themHoaDonVaoMap(List<TongHopHoaDonDTO> listChiTietHoaDon) {
        Map<Long, Integer> danhSachTong = new HashMap<>();
        for (TongHopHoaDonDTO items : listChiTietHoaDon) {
            long name = items.getMaSanPham();
            Integer value = items.getSoLuong();

            if (danhSachTong.containsKey(name)) {
                danhSachTong.put(name, danhSachTong.get(name) + value);
            } else {
                danhSachTong.put(name, value);
            }
        }
        return danhSachTong;
    }

    /**
     * @Author Nguyễn Xuân Long
     * @Decription: update số lượng sản phầm sau khi lập hóa đơn
     */
    @Override
    public int thayDoiSoLuongSanPham(List<TongHopHoaDonDTO> listChiTietHoaDon) {
        for (TongHopHoaDonDTO items : listChiTietHoaDon) {
            SanPham sanPham = sanPhamService.findById(items.getMaSanPham()).orElse(new SanPham());
            sanPham.setSoLuong(-items.getSoLuong());
            sanPhamService.save(sanPham);
        }
        return 1;
    }

    /**
     * @Author Nguyễn Xuân Long
     * @Decription: Kiểm tra khách hàng đã tồn taại trong dữ liệu hya chưa nếu chưa sẽ thêm mới còn tồn tại sẽ lấy khách hàng trong
     * database
     * @return  KhachHang khachHang
     */
    @Override
    public KhachHang themKhachHangMoi(BanHangDTO banHangDTO) {
        KhachHang khachHang = new KhachHang();
        if (khachHangService.findByStatusTrueAndSoDienThoai(banHangDTO.getSoDienThoai()) == null) {
            khachHang = modelMapper.map(banHangDTO, KhachHang.class);
            khachHang = khachHangService.save(khachHang);
        } else {
            khachHang = khachHangService.findByStatusTrueAndSoDienThoai(banHangDTO.getSoDienThoai());
        }

        return khachHang;
    }

    /**
     * @Author Nguyễn Xuân Long
     * @Decription: kiểm tra số lượng sản phầm còn lại trong kho và phát hiện lỗi
     * @return Map<String, String> errors
     */
    @Override
    public Map<String, String> checkSoLuongTrongKho(Map<Long, Integer> danhSachTong, List<TongHopHoaDonDTO> listChiTietHoaDon) {
        Map<String, String> errors = new HashMap<>();
        int index = 0;
        for (TongHopHoaDonDTO items : listChiTietHoaDon) {
            long name = items.getMaSanPham();
            Integer value = items.getSoLuong();
            SanPham sanPham = sanPhamService.findById(items.getMaSanPham()).orElse(new SanPham());

            if (danhSachTong.get(items.getMaSanPham()) > sanPham.getSoLuong()) {
                errors.put("chiTietHoaDonBanHang[" + index + "].soLuong", "So luong san pham con lai " +
                        "khong dap ung yeu cau cua ban hoac san pham khong ton tai ");
            }
            index++;
        }
        return errors;
    }
    /**
     * @Author Nguyễn Xuân Long
     * @Decription: tình tổng số tiền trong hóa đơn chi tiết
     * @return  tongHoadon
     */
    @Override
    public long tongHoaDon(List<ChiTietHoaDonBanHang> chiTietHoaDonBanHangList) {
        long tongHoadon = 0;
        for (ChiTietHoaDonBanHang element : chiTietHoaDonBanHangList) {

            if (element.getGiaBanThuc() >= 0 && element.getSoLuong() >= 0) {
                tongHoadon = tongHoadon + element.getGiaBanThuc() * element.getSoLuong();
            }

        }
        return tongHoadon;
    }


}
