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

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 10/2/2023
*/
@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
    * @Description findAllDTO
    * @Param
    * @Return List<SanPhamDTO>
    */
    @Override
    public List<SanPhamDTO> findAllDTO() {
        return findAll().stream()
                .map(this::getSanPhamDTO)
                .collect(Collectors.toList());
    }

    /**
    * @Description findAll
    * @Param
    * @Return List<SanPham>
    */
    @Override
    public List<SanPham> findAll() {
        return sanPhamRepository.findByStatusTrue();
    }

    /**
    * @Description findById
    * @Param id
    * @Return Optional<SanPham>
    */
    @Override
    public Optional<SanPham> findById(Long id) {
        return sanPhamRepository.findById(id);
    }

    /**
    * @Description findDTOById
    * @Param id
    * @Return Optional<SanPhamDTO>
    */
    @Override
    public Optional<SanPhamDTO> findDTOById(Long id) {
        Optional<SanPham> sanPham = sanPhamRepository.findById(id);
        return sanPham.map(pham -> Optional.ofNullable(getSanPhamDTO(pham)))
                .orElse(null);
    }

    /**
    * @Description save
    * @Param sanPham
    * @Return SanPhamDTO
    */
    @Override
    public SanPhamDTO save(SanPhamDTO sanPham) {
        return getSanPhamDTO(sanPhamRepository.save(getSanPham(sanPham)));
    }

    /**
    * @Description delete
    * @Param id
    * @Return SanPhamDTO
    */
    @Override
    public SanPhamDTO delete(Long id) {
        Optional<SanPham> sanPham = findById(id);
        if (sanPham.isPresent()) {
            sanPham.get().setStatus(false);
            return getSanPhamDTO(sanPhamRepository.save(sanPham.get()));
        }
        return null;
    }

    /**
    * @Description update
    * @Param sanPham
    * @Return SanPhamDTO
    */
    @Override
    public SanPhamDTO update(SanPhamDTO sanPhamDTO) {
        return sanPhamDTO == null || sanPhamDTO.getMaSanPham() == 0
                ? null
                : getSanPhamDTO(sanPhamRepository.save(getSanPham(sanPhamDTO)));
    }

    /**
    * @Description updateInventory
    * @Param maSanPham
    * @Param soLuong
    * @Return
    */
    @Override
    public void updateInventory(long maSanPham, int soLuong) {
        Optional<SanPham> sanPham = findById(maSanPham);
        sanPham.ifPresent(item -> item.setSoLuong(soLuong));
    }

    /**
    * @Description getSanPhamDTO
    * @Param sanPham
    * @Return SanPhamDTO
    */
    private SanPhamDTO getSanPhamDTO(SanPham sanPham) {
        return modelMapper.map(sanPham, SanPhamDTO.class);
    }

    /**
    * @Description getSanPham
    * @Param sanPham
    * @Return SanPham
    */
    private SanPham getSanPham(SanPhamDTO sanPhamDTO) {
        return modelMapper.map(sanPhamDTO, SanPham.class);
    }
}
