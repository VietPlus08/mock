package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.KhachHangDTO;
import fa.com.mock_back_end.entity.KhachHang;
import fa.com.mock_back_end.repository.KhachHangRepository;
import fa.com.mock_back_end.service.KhachHangService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<KhachHangDTO> findAllDTO() {
        return findAll().stream()
                .map(this::getKhachHangDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KhachHang> findAll() {
        return khachHangRepository.findByStatusTrue();
    }

    @Override
    public Optional<KhachHang> findById(Long id) {
        return khachHangRepository.findById(id);
    }

    @Override
    public KhachHang save(KhachHang data) {
        return khachHangRepository.save(data);
    }

    @Override
    public KhachHang delete(Long id) {
        Optional<KhachHang> khachHang = findById(id);
        if (khachHang.isPresent()) {
            khachHang.get().setStatus(false);
            return khachHangRepository.save(khachHang.get());
        }
        return null;
    }

    @Override
    public KhachHang update(KhachHang data) {
        Optional<KhachHang> khachHang = findById(data.getMaKhachHang());
        if (khachHang.isPresent()){
            khachHang.get().setTenKhachHang(data.getTenKhachHang());
            khachHang.get().setGioiTinh(data.getGioiTinh());
            khachHang.get().setNgaySinh(data.getNgaySinh());
            khachHang.get().setSoDienThoai(data.getSoDienThoai());
            return khachHangRepository.save(khachHang.get());
        }
        return null;
    }

    private KhachHangDTO getKhachHangDTO(KhachHang khachHang){
        return modelMapper.map(khachHang, KhachHangDTO.class);
    }
}
