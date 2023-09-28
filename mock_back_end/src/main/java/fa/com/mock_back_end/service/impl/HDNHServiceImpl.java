package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.ChiTietNhapHangDTO;
import fa.com.mock_back_end.dto.NhapHangDTO;
import fa.com.mock_back_end.dto.SanPhamDTO;
import fa.com.mock_back_end.entity.ChiTietHoaDonNhapHang;
import fa.com.mock_back_end.entity.HoaDonNhapHang;
import fa.com.mock_back_end.entity.NhaCungCap;
import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.repository.HDNHRepository;
import fa.com.mock_back_end.service.ChiTietHDNHService;
import fa.com.mock_back_end.service.HDNHService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HDNHServiceImpl implements HDNHService {

    @Autowired
    HDNHRepository hdnhRepository;

    @Autowired
    ChiTietHDNHService chiTietHDNHService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<NhapHangDTO> findAllDTO() {
        return findAll().stream()
                .map(this::getNhapHangDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HoaDonNhapHang> findAll() {
        return hdnhRepository.findByStatusTrue();
    }

    @Override
    public Optional<HoaDonNhapHang> findById(Long id) {
        return hdnhRepository.findById(id);
    }

    @Override
    public HoaDonNhapHang save(NhapHangDTO data) {
        List<ChiTietNhapHangDTO> listChiTietHoaDonNhapHangDTO = data.getChiTietHoaDonNhapHangDTO();
        HoaDonNhapHang nhapHang = new HoaDonNhapHang();
        nhapHang.setNhaCungCap(new NhaCungCap(data.getMaNhaCungCap()));
        nhapHang.setTongHoaDon(getTongTien(listChiTietHoaDonNhapHangDTO));
        HoaDonNhapHang savedHoaDonNhapHang = hdnhRepository.save(nhapHang);
        // set MaHoaDon vào từng chi tiết hóa đơn trước khi lưu
        List<ChiTietHoaDonNhapHang> listChiTietHoaDonNhapHang = getListChiTietHoaDonNhapHang(listChiTietHoaDonNhapHangDTO, savedHoaDonNhapHang.getMaHoaDonNhapHang());
        chiTietHDNHService.saveAll(listChiTietHoaDonNhapHang);
        savedHoaDonNhapHang.setListChiTietHoaDonNhapHang(listChiTietHoaDonNhapHang);
        return savedHoaDonNhapHang;
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
        if (hoaDonNhapHang.isPresent()) {
            hoaDonNhapHang.get().setTongHoaDon(data.getTongHoaDon());
            hoaDonNhapHang.get().setNhaCungCap(data.getNhaCungCap());
            return hdnhRepository.save(hoaDonNhapHang.get());
        }
        return null;
    }

    private static long getTongTien(List<ChiTietNhapHangDTO> listChiTietNhapHangDTO) {
        return listChiTietNhapHangDTO.stream()
                .mapToLong(i -> i.getSanPhamDTO().getGiaVon() * i.getSoLuong())
                .sum();
    }

    private List<ChiTietHoaDonNhapHang> getListChiTietHoaDonNhapHang(List<ChiTietNhapHangDTO> listChiTietNhapHangDTO,
                                                                     long maHoaDonNhapHang) {
        return listChiTietNhapHangDTO.stream()
                .map(i -> getChiTietHoaDonNhapHang(i, maHoaDonNhapHang))
                .collect(Collectors.toList());
    }

    private ChiTietHoaDonNhapHang getChiTietHoaDonNhapHang(ChiTietNhapHangDTO chiTietNhapHangDTO,
                                                           long maHoaDonNhapHang) {
        ChiTietHoaDonNhapHang chiTietHoaDonNhapHang = modelMapper.map(chiTietNhapHangDTO, ChiTietHoaDonNhapHang.class);
        chiTietHoaDonNhapHang.setHoaDonNhapHang(new HoaDonNhapHang(maHoaDonNhapHang));
        return chiTietHoaDonNhapHang;
    }

    private NhapHangDTO getNhapHangDTO(HoaDonNhapHang nhapHang) {
        NhapHangDTO nhapHangDTO = modelMapper.map(nhapHang, NhapHangDTO.class);
        NhaCungCap nhaCungCap = nhapHang.getNhaCungCap();
        if (nhaCungCap != null) {
            nhapHangDTO.setMaNhaCungCap(nhaCungCap.getMaNhaCungCap());
            nhapHangDTO.setTenNhaCungCap(nhaCungCap.getTenNhaCungCap());
        }
        nhapHangDTO.setChiTietHoaDonNhapHangDTO(getListChiTietNhapHangDTO(nhapHang.getListChiTietHoaDonNhapHang()));
        return nhapHangDTO;
    }

    private List<ChiTietNhapHangDTO> getListChiTietNhapHangDTO(List<ChiTietHoaDonNhapHang> data) {
        return data == null
                ? new ArrayList<>()
                : data.stream()
                .map(this::getChiTietNhapHangDTO)
                .collect(Collectors.toList());
    }

    private ChiTietNhapHangDTO getChiTietNhapHangDTO(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang) {
        ChiTietNhapHangDTO chiTietNhapHangDTO = modelMapper.map(chiTietHoaDonNhapHang, ChiTietNhapHangDTO.class);
        chiTietNhapHangDTO.setSanPhamDTO(getSanPhamDTO(chiTietHoaDonNhapHang.getSanPham()));
        return chiTietNhapHangDTO;
    }

    private SanPhamDTO getSanPhamDTO(SanPham sanPham) {
        return modelMapper.map(sanPham, SanPhamDTO.class);
    }
}
