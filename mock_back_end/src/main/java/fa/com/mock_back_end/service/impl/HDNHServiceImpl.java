package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.ChiTietHoaDonNhapHangDTO;
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
    HDNHRepository HDNHRepository;

    @Autowired
    ChiTietHDNHService chiTietHDNHService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    ModelMapper modelMapper;

    /**
    * @Description findAllDTO
    * @Param
    * @Return List<NhapHangDTO>
    */
    @Override
    public List<NhapHangDTO> findAllDTO() {
        return findAll().stream()
                .map(this::getNhapHangDTO)
                .collect(Collectors.toList());
    }

    /**
    * @Description findAll
    * @Param
    * @Return List<HoaDonNhapHang>
    */
    @Override
    public List<HoaDonNhapHang> findAll() {
        return HDNHRepository.findByStatusTrue();
    }

    /**
    * @Description findById
    * @Param id
    * @Return Optional<HoaDonNhapHang>
    */
    @Override
    public Optional<HoaDonNhapHang> findById(Long id) {
        return HDNHRepository.findById(id);
    }

    /**
    * @Description save
    * @Param nhapHangDTO
    * @Return NhapHangDTO
    */
    @Override
    public NhapHangDTO save(NhapHangDTO nhapHangDTO) {
        List<ChiTietHoaDonNhapHangDTO> listChiTietHoaDonNhapHangDTO = nhapHangDTO.getChiTietHoaDonNhapHangDTO();
        HoaDonNhapHang nhapHang = new HoaDonNhapHang();
        if (nhapHangDTO.getMaNhaCungCap() != 0) {
            nhapHang.setNhaCungCap(new NhaCungCap(nhapHangDTO.getMaNhaCungCap()));
        }
        nhapHang.setTongHoaDon(getTongTien(listChiTietHoaDonNhapHangDTO));
        // lưu hóa đơn
        HoaDonNhapHang savedHoaDonNhapHang = HDNHRepository.save(nhapHang);
        // lưu chi tiết hóa đơn
        List<ChiTietHoaDonNhapHang> listChiTietHoaDonNhapHang = getListChiTietHoaDonNhapHang(listChiTietHoaDonNhapHangDTO, savedHoaDonNhapHang.getMaHoaDonNhapHang());
        chiTietHDNHService.saveAll(listChiTietHoaDonNhapHang);
        // set chi tiết hóa đơn vào data response
        savedHoaDonNhapHang.setListChiTietHoaDonNhapHang(listChiTietHoaDonNhapHang);
        return getNhapHangDTO(savedHoaDonNhapHang);
    }

    /**
    * @Description delete
    * @Param id
    * @Return HoaDonNhapHang
    */
    @Override
    public HoaDonNhapHang delete(Long id) {
        Optional<HoaDonNhapHang> hoaDonNhapHang = findById(id);
        if (hoaDonNhapHang.isPresent()) {
            hoaDonNhapHang.get().setStatus(false);
            return HDNHRepository.save(hoaDonNhapHang.get());
        }
        return null;
    }

    /**
    * @Description update
    * @Param nhapHangDTO
    * @Return NhapHangDTO
    */
    @Override
    public NhapHangDTO update(NhapHangDTO nhapHangDTO) {
        return nhapHangDTO == null || nhapHangDTO.getMaHoaDonNhapHang() == 0
                ? null
                : save(nhapHangDTO);
    }

    /**
    * @Description getTongTien
    * @Param listChiTietNhapHangDTO
    * @Return long
    */
    public long getTongTien(List<ChiTietHoaDonNhapHangDTO> listChiTietHoaDonNhapHangDTO) {
        List<SanPham> sanPhams = sanPhamService.findAll();
        return listChiTietHoaDonNhapHangDTO.stream()
                .mapToLong(i -> getGiaVon(sanPhams, i.getSanPhamDTO().getMaSanPham()) * i.getSoLuong())
                .sum();
    }

    /**
    * @Description getGiaVon
    * @Param list
    * @Param maSanPham
    * @Return long
    */
    public long getGiaVon(List<SanPham> list, long maSanPham){
        return list.stream()
                .filter(i -> i.getMaSanPham() == maSanPham)
                .mapToLong(SanPham::getGiaVon)
                .findFirst()
                .orElse(0);
    }

    /**
    * @Description getListChiTietHoaDonNhapHang
    * @Param listChiTietNhapHangDTO
    * @Param maHoaDonNhapHang
    * @Return List<ChiTietHoaDonNhapHang>
    */
    public List<ChiTietHoaDonNhapHang> getListChiTietHoaDonNhapHang(List<ChiTietHoaDonNhapHangDTO> listChiTietHoaDonNhapHangDTO,
                                                                    long maHoaDonNhapHang) {
        return listChiTietHoaDonNhapHangDTO.stream()
                .map(i -> getChiTietHoaDonNhapHang(i, maHoaDonNhapHang))
                .collect(Collectors.toList());
    }

    /**
    * @Description getChiTietHoaDonNhapHang
    * @Param chiTietNhapHangDTO
    * @Param maHoaDonNhapHang
    * @Return ChiTietHoaDonNhapHang
    */
    public ChiTietHoaDonNhapHang getChiTietHoaDonNhapHang(ChiTietHoaDonNhapHangDTO chiTietHoaDonNhapHangDTO,
                                                          long maHoaDonNhapHang) {
        ChiTietHoaDonNhapHang chiTietHoaDonNhapHang = modelMapper.map(chiTietHoaDonNhapHangDTO, ChiTietHoaDonNhapHang.class);
        chiTietHoaDonNhapHang.setHoaDonNhapHang(new HoaDonNhapHang(maHoaDonNhapHang));
        chiTietHoaDonNhapHang.setSanPham(new SanPham(chiTietHoaDonNhapHangDTO.getSanPhamDTO().getMaSanPham()));
        // điều chỉnh hàng tồn kho (số lượng trong sản phẩm)
        sanPhamService.updateInventory(chiTietHoaDonNhapHangDTO.getSanPhamDTO().getMaSanPham(), chiTietHoaDonNhapHangDTO.getSoLuong());
        return chiTietHoaDonNhapHang;
    }

    /**
    * @Description getNhapHangDTO
    * @Param nhapHang
    * @Return NhapHangDTO
    */
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

    /**
    * @Description getListChiTietNhapHangDTO
    * @Param list
    * @Return List<ChiTietNhapHangDTO>
    */
    public List<ChiTietHoaDonNhapHangDTO> getListChiTietNhapHangDTO(List<ChiTietHoaDonNhapHang> list) {
        return list == null
                ? new ArrayList<>()
                : list.stream()
                .map(this::getChiTietNhapHangDTO)
                .collect(Collectors.toList());
    }

    /**
    * @Description getChiTietNhapHangDTO
    * @Param chiTietHoaDonNhapHang
    * @Return ChiTietNhapHangDTO
    */
    public ChiTietHoaDonNhapHangDTO getChiTietNhapHangDTO(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang) {
        ChiTietHoaDonNhapHangDTO chiTietHoaDonNhapHangDTO = modelMapper.map(chiTietHoaDonNhapHang, ChiTietHoaDonNhapHangDTO.class);
        chiTietHoaDonNhapHangDTO.setSanPhamDTO(getSanPhamDTO(chiTietHoaDonNhapHang.getSanPham()));
        return chiTietHoaDonNhapHangDTO;
    }

    /**
    * @Description getSanPhamDTO
    * @Param sanPham
    * @Return SanPhamDTO
    */
    public SanPhamDTO getSanPhamDTO(SanPham sanPham) {
        return modelMapper.map(sanPham, SanPhamDTO.class);
    }
}
