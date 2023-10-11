package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.NhanVienDTO;
import fa.com.mock_back_end.dto.SanPhamDTO;
import fa.com.mock_back_end.dto.ThongKeDTO;
import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;
import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.service.ChiTietHDBHService;
import fa.com.mock_back_end.service.HDBHService;
import fa.com.mock_back_end.service.NhanVienService;
import fa.com.mock_back_end.service.SanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThongKeServiceImpl {

    @Autowired
    HDBHService hdbhService;

    @Autowired
    ChiTietHDBHService chiTietHDBHService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    ModelMapper modelMapper;

    public ThongKeDTO getThongKe(ThongKeDTO thongKeDTO) {
        if ("thongKeSanPham".equals(thongKeDTO.getLoaiThongKe())) {
            thongKeSanPhamProcess(thongKeDTO);
        } else if ("thongKeNhanVien".equals(thongKeDTO.getLoaiThongKe())) {
            thongKeNhanVienProcess(thongKeDTO);
        } else {
            return null;
        }
        return thongKeDTO;
    }

    private void thongKeNhanVienProcess(ThongKeDTO thongKeDTO) {
        List<NhanVienDTO> listNhanVienDTO = findListNhanVienDTO(thongKeDTO);
        thongKeDTO.setListNhanVien(listNhanVienDTO);
        listNhanVienDTO.forEach(i -> {
            thongKeDTO.setTongDoanhThu(i.getDoanhThu());
            thongKeDTO.setTongLoiNhuan(i.getLoiNhuan());
            thongKeDTO.setTongSoLuong(i.getSoLuongBan());
        });
    }

    /**
     * @Description findListNhanVienDTO
     * @Param thongKeDTO
     * @Return NhanVienDTO
     */
    private List<NhanVienDTO> findListNhanVienDTO(ThongKeDTO thongKeDTO) {
        return nhanVienService.findAll().stream()
                .map(i -> getNhanVienDTO(i, thongKeDTO))
                .collect(Collectors.toList());
    }


    private NhanVienDTO getNhanVienDTO(NhanVien nhanVien, ThongKeDTO thongKeDTO) {
        NhanVienDTO nhanVienDTO = modelMapper.map(nhanVien, NhanVienDTO.class);
        List<HoaDonBanHang> listHoaDonBanHang = nhanVien.getListHoaDonBanHang();
        listHoaDonBanHang.forEach(hd -> {
            LocalDate thoiGianBanHang = hd.getThoiGianBanHang().toLocalDate();
            if ((thoiGianBanHang.isAfter(thongKeDTO.getThoiGianBatDau())
                    || thoiGianBanHang.isEqual(thongKeDTO.getThoiGianBatDau()))
                    && thoiGianBanHang.isBefore(thongKeDTO.getThoiGianKetThuc())
                    || thoiGianBanHang.isEqual(thongKeDTO.getThoiGianKetThuc())) {
                hd.getListChiTietHoaDonBanHang().forEach(cthdbh -> {
                    nhanVienDTO.setDoanhThu(cthdbh.getSoLuong() * cthdbh.getGiaBanThuc());
                    nhanVienDTO.setLoiNhuan(cthdbh.getSoLuong() * (cthdbh.getGiaBanThuc() - cthdbh.getSanPham().getGiaVon()));
                    nhanVienDTO.setSoLuongBan(cthdbh.getSoLuong());
                });
            }
        });
        return nhanVienDTO;
    }

    private void thongKeSanPhamProcess(ThongKeDTO thongKeDTO) {
        List<SanPhamDTO> listSanPhamDTO = findListSanPhamDTO(thongKeDTO);
        thongKeDTO.setListSanPham(listSanPhamDTO);
        listSanPhamDTO.forEach(i -> {
            thongKeDTO.setTongDoanhThu(i.getDoanhThu());
            thongKeDTO.setTongLoiNhuan(i.getLoiNhuan());
            thongKeDTO.setTongSoLuong(i.getSoLuong());
        });
    }

    public List<SanPhamDTO> findListSanPhamDTO(ThongKeDTO thongKeDTO) {
        return sanPhamService.findAll().stream()
                .map(i -> getSanPhamDTO(i, thongKeDTO))
                .collect(Collectors.toList());
    }

    private SanPhamDTO getSanPhamDTO(SanPham sanPham, ThongKeDTO thongKeDTO) {
        SanPhamDTO sanPhamDTO = modelMapper.map(sanPham, SanPhamDTO.class);
        List<ChiTietHoaDonBanHang> listChiTietHoaDonBanHang = sanPham.getListChiTietHoaDonBanHang();
        listChiTietHoaDonBanHang.forEach(i -> {
            LocalDate thoiGianBanHang = i.getHoaDonBanHang().getThoiGianBanHang().toLocalDate();
            if ((thoiGianBanHang.isAfter(thongKeDTO.getThoiGianBatDau())
                        || thoiGianBanHang.isEqual(thongKeDTO.getThoiGianBatDau()))
                    && thoiGianBanHang.isBefore(thongKeDTO.getThoiGianKetThuc())
                        || thoiGianBanHang.isEqual(thongKeDTO.getThoiGianKetThuc())) {
                sanPhamDTO.setDoanhThu(i.getSoLuong() * i.getGiaBanThuc());
                sanPhamDTO.setLoiNhuan(i.getSoLuong() * (i.getGiaBanThuc() - sanPhamDTO.getGiaVon()));
                sanPhamDTO.setSoLuongBan(i.getSoLuong());
            }
        });
        return sanPhamDTO;
    }
}
