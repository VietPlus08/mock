package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.NhanVienDTO;
import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.generator.Generator;
import fa.com.mock_back_end.repository.NhanVienRepository;
import fa.com.mock_back_end.service.NhanVienService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    UserInforServiceImpl userInforService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<NhanVienDTO> findAllDTO() {
        return findAll().stream()
                .map(this::getNhanVienDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NhanVien> findAll() {
        return nhanVienRepository.findByStatusTrue();
    }

    @Override
    public Optional<NhanVien> findById(String id) {
        return nhanVienRepository.findById(id);
    }

    @Override
    public NhanVienDTO save(NhanVienDTO nhanVien) {
        if (nhanVien.getMaNhanVien() == null) {
            nhanVien.setMaNhanVien(Generator.getMaNhanVien(nhanVienRepository.getByMaNhanVien()));
        }
        return getNhanVienDTO(nhanVienRepository.save(getNhanVien(nhanVien)));
    }

    @Override
    public NhanVienDTO delete(String id) {
        Optional<NhanVien> nhanVien = findById(id);
        if (nhanVien.isPresent()) {
            nhanVien.get().setStatus(false);
            return getNhanVienDTO(nhanVienRepository.save(nhanVien.get()));
        }
        return null;
    }

    @Override
    public NhanVienDTO update(NhanVienDTO nhanVienDTO) {
        if (nhanVienDTO.getPassword() == null) {
            nhanVienRepository.updateNhanVienInfor(nhanVienDTO.getTenNhanVien(), nhanVienDTO.getNgaySinh(), nhanVienDTO.getGioiTinh(), nhanVienDTO.getMaNhanVien());
        } else {
            nhanVienRepository.updateNhanVienPassword(passwordEncoder.encode(nhanVienDTO.getPassword()), nhanVienDTO.getMaNhanVien());
        }
        return nhanVienDTO;
    }

    public NhanVienDTO getNhanVienDTO(NhanVien nhanVien) {
        return modelMapper.map(nhanVien, NhanVienDTO.class);
    }

    public NhanVien getNhanVien(NhanVienDTO nhanVienDTO) {
        NhanVien nhanVien = modelMapper.map(nhanVienDTO, NhanVien.class);
        if (nhanVienDTO.getPassword() != null) {
            nhanVien.setMatKhau(passwordEncoder.encode(nhanVienDTO.getPassword()));
        }
        return nhanVien;
    }
}
