package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.dto.NhanVienDTO;
import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.generator.Generator;
import fa.com.mock_back_end.repository.NhanVienRepository;
import fa.com.mock_back_end.service.NhanVienService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    ModelMapper modelMapper;

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
    public NhanVien save(NhanVien nhanVien) {
        if (nhanVien.getMaNhanVien() == null) {
            nhanVien.setMaNhanVien(Generator.getMaNhanVien(nhanVienRepository.getByMaNhanVien()));
        }
        return nhanVienRepository.save(nhanVien);
    }

    @Override
    public NhanVien delete(String id) {
        Optional<NhanVien> nhanVien = findById(id);
        if (nhanVien.isPresent()) {
            nhanVien.get().setStatus(false);
            return nhanVienRepository.save(nhanVien.get());
        }
        return null;
    }

    @Override
    public NhanVien update(NhanVien updateNhanVien) {
        Optional<NhanVien> nhanVien = findById(updateNhanVien.getMaNhanVien());
        if (nhanVien.isPresent()) {
            nhanVien.get().setTenNhanVien(updateNhanVien.getTenNhanVien());
            nhanVien.get().setNgaySinh(updateNhanVien.getNgaySinh());
            nhanVien.get().setGioiTinh(updateNhanVien.getGioiTinh());
            nhanVien.get().setMatKhau(updateNhanVien.getMatKhau());
            nhanVien.get().setChucVu(updateNhanVien.getChucVu());
            return save(nhanVien.get());
        }
        return null;
    }

    private NhanVienDTO getNhanVienDTO(NhanVien nhanVien){
        return modelMapper.map(nhanVien, NhanVienDTO.class);
    }
}
