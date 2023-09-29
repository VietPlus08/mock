package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.NhaCungCapDTO;
import fa.com.mock_back_end.entity.NhaCungCap;
import fa.com.mock_back_end.repository.NhaCungCapRepository;
import fa.com.mock_back_end.service.NhaCungCapService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {

    @Autowired
    NhaCungCapRepository nhaCungCapRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<NhaCungCapDTO> findAllDTO() {
        return findAll().stream()
                .map(this::getNhaCungCapDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NhaCungCap> findAll() {
        return nhaCungCapRepository.findByStatusTrue();
    }

    @Override
    public Optional<NhaCungCap> findById(Long id) {
        return nhaCungCapRepository.findById(id);
    }

    @Override
    public NhaCungCap save(NhaCungCap data) {
        return nhaCungCapRepository.save(data);
    }

    @Override
    public NhaCungCap delete(Long id) {
        Optional<NhaCungCap> nhaCungCap = findById(id);
        if (nhaCungCap.isPresent()) {
            nhaCungCap.get().setStatus(false);
            return nhaCungCapRepository.save(nhaCungCap.get());
        }
        return null;
    }

    @Override
    public NhaCungCap update(NhaCungCap data) {
        Optional<NhaCungCap> nhaCungCap = findById(data.getMaNhaCungCap());
        if (nhaCungCap.isPresent()){
            nhaCungCap.get().setTenNhaCungCap(data.getTenNhaCungCap());
            nhaCungCap.get().setEmail(data.getEmail());
            nhaCungCap.get().setDiaChi(data.getDiaChi());
            nhaCungCap.get().setSoDienThoai(data.getSoDienThoai());
            nhaCungCap.get().setListHoaDonNhapHang(null);
            return nhaCungCapRepository.save(nhaCungCap.get());
        }
        return null;
    }

    private NhaCungCapDTO getNhaCungCapDTO(NhaCungCap nhaCungCap){
        return modelMapper.map(nhaCungCap, NhaCungCapDTO.class);
    }
}
