package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.NhaCungCapDTO;
import fa.com.mock_back_end.entity.NhaCungCap;
import fa.com.mock_back_end.repository.NhaCungCapRepository;
import fa.com.mock_back_end.service.NhaCungCapService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<NhaCungCapDTO> findDTOById(Long id) {
        Optional<NhaCungCap> nhaCungCap = nhaCungCapRepository.findById(id);
        return nhaCungCap.map(cungCap -> Optional.ofNullable(getNhaCungCapDTO(cungCap)))
                .orElse(null);
    }

    @Override
    public NhaCungCapDTO save(NhaCungCapDTO data) {
        return getNhaCungCapDTO(nhaCungCapRepository.save(getNhaCungCap(data)));
    }

    @Override
    public NhaCungCapDTO delete(Long id) {
        Optional<NhaCungCap> nhaCungCap = findById(id);
        if (nhaCungCap.isPresent()) {
            nhaCungCap.get().setStatus(false);
            return getNhaCungCapDTO(nhaCungCapRepository.save(nhaCungCap.get()));
        }
        return null;
    }

    @Override
    public NhaCungCapDTO update(NhaCungCapDTO data) {
        if (data == null || data.getMaNhaCungCap() == null) {
            return null;
        }
        return getNhaCungCapDTO(nhaCungCapRepository.save(getNhaCungCap(data)));
    }

    private NhaCungCapDTO getNhaCungCapDTO(NhaCungCap nhaCungCap) {
        return modelMapper.map(nhaCungCap, NhaCungCapDTO.class);
    }

    private NhaCungCap getNhaCungCap(NhaCungCapDTO nhaCungCapDTO) {
        return modelMapper.map(nhaCungCapDTO, NhaCungCap.class);
    }
}
