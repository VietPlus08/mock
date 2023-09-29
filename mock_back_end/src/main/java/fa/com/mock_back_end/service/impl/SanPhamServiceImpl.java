package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.SanPhamDTO;
import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.repository.SanPhamRepository;
import fa.com.mock_back_end.service.SanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<SanPhamDTO> findAllDTO() {
        return findAll().stream()
                .map(this::getSanPhamDTO)
                .collect(Collectors.toList());
    }

    public SanPhamDTO getSanPhamDTO(SanPham sanPham){
        return modelMapper.map(sanPham, SanPhamDTO.class);
    }

    @Override
    public List<SanPham> findAll() {
        return sanPhamRepository.findByStatusTrue();
    }

    @Override
    public Optional<SanPham> findById(Long id) {
        return sanPhamRepository.findById(id);
    }

    @Override
    public SanPham save(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham delete(Long id) {
        Optional<SanPham> sanPham = findById(id);
        if (sanPham.isPresent()) {
            sanPham.get().setStatus(false);
            return save(sanPham.get());
        }
        return null;
    }

    @Override
    public SanPham update(SanPham updateSanPham) {
        Optional<SanPham> sanPham = findById(updateSanPham.getMaSanPham());
        if (sanPham.isPresent()) {
            sanPham.get().setTenSanPham(updateSanPham.getTenSanPham());
            sanPham.get().setNhanHang(updateSanPham.getNhanHang());
            sanPham.get().setBoNhoTrong(updateSanPham.getBoNhoTrong());
            sanPham.get().setGiaVon(updateSanPham.getGiaVon());
            sanPham.get().setGiaNiemYet(updateSanPham.getGiaNiemYet());
            sanPham.get().setMauSac(updateSanPham.getMauSac());
            sanPham.get().setRam(updateSanPham.getRam());
            sanPham.get().setCamera(updateSanPham.getCamera());
            sanPham.get().setImgUrl(updateSanPham.getImgUrl());
            sanPham.get().setListChiTietHoaDonNhapHang(null);
            sanPham.get().setListChiTietHoaDonBanHang(null);
            return save(sanPham.get());
        }
        return null;
    }
}
