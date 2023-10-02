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
    public Optional<KhachHangDTO> findDTOById(Long id) {
        Optional<KhachHang> khachHang = khachHangRepository.findById(id);
        return khachHang.map(hang -> Optional.ofNullable(getKhachHangDTO(hang)))
                .orElse(null);
    }

    @Override
    public Optional<KhachHang> findById(Long id) {
        return khachHangRepository.findById(id);
    }

    @Override
    public KhachHangDTO save(KhachHangDTO data) {
        return getKhachHangDTO(khachHangRepository.save(getKhachHang(data)));
    }

    @Override
    public KhachHangDTO delete(Long id) {
        Optional<KhachHang> khachHang = findById(id);
        if (khachHang.isPresent()) {
            khachHang.get().setStatus(false);
            return getKhachHangDTO(khachHangRepository.save(khachHang.get()));
        }
        return null;
    }

    @Override
    public KhachHangDTO update(KhachHangDTO data) {
        if (data == null || data.getMaKhachHang() == 0) {
            return null;
        }
        return getKhachHangDTO(khachHangRepository.save(getKhachHang(data)));
    }

    private KhachHangDTO getKhachHangDTO(KhachHang khachHang) {
        return modelMapper.map(khachHang, KhachHangDTO.class);
    }

    private KhachHang getKhachHang(KhachHangDTO khachHangDTO) {
        return modelMapper.map(khachHangDTO, KhachHang.class);
    }
}
