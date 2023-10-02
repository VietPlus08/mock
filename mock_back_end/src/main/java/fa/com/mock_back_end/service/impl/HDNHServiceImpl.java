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
import fa.com.mock_back_end.service.SanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 10/2/2023
*/
@Service
public class HDNHServiceImpl implements HDNHService {

    @Autowired
    HDNHRepository hdnhRepository;

    @Autowired
    ChiTietHDNHService chiTietHDNHService;

    @Autowired
    SanPhamService sanPhamService;

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
    public NhapHangDTO save(NhapHangDTO data) {
        List<ChiTietNhapHangDTO> listChiTietHoaDonNhapHangDTO = data.getChiTietHoaDonNhapHangDTO();
        HoaDonNhapHang nhapHang = new HoaDonNhapHang();
        if (data.getMaNhaCungCap() != 0) {
            nhapHang.setNhaCungCap(new NhaCungCap(data.getMaNhaCungCap()));
        }
        nhapHang.setTongHoaDon(getTongTien(listChiTietHoaDonNhapHangDTO));
        // lưu hóa đơn
        HoaDonNhapHang savedHoaDonNhapHang = hdnhRepository.save(nhapHang);
        // lưu chi tiết hóa đơn
        List<ChiTietHoaDonNhapHang> listChiTietHoaDonNhapHang = getListChiTietHoaDonNhapHang(listChiTietHoaDonNhapHangDTO, savedHoaDonNhapHang.getMaHoaDonNhapHang());
        chiTietHDNHService.saveAll(listChiTietHoaDonNhapHang);
        // set chi tiết hóa đơn vào data response
        savedHoaDonNhapHang.setListChiTietHoaDonNhapHang(listChiTietHoaDonNhapHang);
        return getNhapHangDTO(savedHoaDonNhapHang);
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
    public NhapHangDTO update(NhapHangDTO nhapHangDTO) {
        return nhapHangDTO == null || nhapHangDTO.getMaHoaDonNhapHang() == 0
                ? null
                : save(nhapHangDTO);
    }

    public long getTongTien(List<ChiTietNhapHangDTO> listChiTietNhapHangDTO) {
        List<SanPham> sanPhams = sanPhamService.findAll();
        Long giaVon = sanPhams.stream().filter(i -> i.getMaSanPham() == 1).mapToLong(SanPham::getGiaVon).findAny().orElse(0);
        return listChiTietNhapHangDTO.stream()
                .mapToLong(i -> getGiaVon(sanPhams, i.getSanPhamDTO().getMaSanPham()) * i.getSoLuong())
                .sum();
    }

    public long getGiaVon(List<SanPham> list, long maSanPham){
        return list.stream()
                .filter(i -> i.getMaSanPham() == maSanPham)
                .mapToLong(SanPham::getGiaVon)
                .findAny()
                .orElse(0);
    }

    public List<ChiTietHoaDonNhapHang> getListChiTietHoaDonNhapHang(List<ChiTietNhapHangDTO> listChiTietNhapHangDTO,
                                                                    long maHoaDonNhapHang) {
        return listChiTietNhapHangDTO.stream()
                .map(i -> getChiTietHoaDonNhapHang(i, maHoaDonNhapHang))
                .collect(Collectors.toList());
    }

    public ChiTietHoaDonNhapHang getChiTietHoaDonNhapHang(ChiTietNhapHangDTO chiTietNhapHangDTO,
                                                          long maHoaDonNhapHang) {
        ChiTietHoaDonNhapHang chiTietHoaDonNhapHang = modelMapper.map(chiTietNhapHangDTO, ChiTietHoaDonNhapHang.class);
        chiTietHoaDonNhapHang.setHoaDonNhapHang(new HoaDonNhapHang(maHoaDonNhapHang));
        chiTietHoaDonNhapHang.setSanPham(new SanPham(chiTietNhapHangDTO.getSanPhamDTO().getMaSanPham()));
        // điều chỉnh hàng tồn kho (số lượng trong sản phẩm)
        sanPhamService.updateInventory(chiTietNhapHangDTO.getSanPhamDTO().getMaSanPham(), chiTietNhapHangDTO.getSoLuong());
        return chiTietHoaDonNhapHang;
    }

    public NhapHangDTO getNhapHangDTO(HoaDonNhapHang nhapHang) {
        NhapHangDTO nhapHangDTO = modelMapper.map(nhapHang, NhapHangDTO.class);
        NhaCungCap nhaCungCap = nhapHang.getNhaCungCap() == null
                ? null : nhapHang.getNhaCungCap();
        if (nhaCungCap != null) {
            nhapHangDTO.setMaNhaCungCap(nhaCungCap.getMaNhaCungCap());
            nhapHangDTO.setTenNhaCungCap(nhaCungCap.getTenNhaCungCap());
        }
        nhapHangDTO.setChiTietHoaDonNhapHangDTO(getListChiTietNhapHangDTO(nhapHang.getListChiTietHoaDonNhapHang()));
        return nhapHangDTO;
    }

    public List<ChiTietNhapHangDTO> getListChiTietNhapHangDTO(List<ChiTietHoaDonNhapHang> data) {
        return data == null
                ? new ArrayList<>()
                : data.stream()
                .map(this::getChiTietNhapHangDTO)
                .collect(Collectors.toList());
    }

    public ChiTietNhapHangDTO getChiTietNhapHangDTO(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang) {
        ChiTietNhapHangDTO chiTietNhapHangDTO = modelMapper.map(chiTietHoaDonNhapHang, ChiTietNhapHangDTO.class);
        chiTietNhapHangDTO.setSanPhamDTO(getSanPhamDTO(chiTietHoaDonNhapHang.getSanPham()));
        return chiTietNhapHangDTO;
    }

    public SanPhamDTO getSanPhamDTO(SanPham sanPham) {
        return modelMapper.map(sanPham, SanPhamDTO.class);
    }
}
