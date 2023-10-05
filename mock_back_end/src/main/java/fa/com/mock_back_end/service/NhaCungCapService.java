package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.NhaCungCapDTO;
import fa.com.mock_back_end.entity.NhaCungCap;

import java.util.List;
import java.util.Optional;

public interface NhaCungCapService {

    List<NhaCungCapDTO> findAllDTO();
    List<NhaCungCap> findAll();
    Optional<NhaCungCap> findById(Long id);
    Optional<NhaCungCapDTO> findDTOById(Long id);
    NhaCungCapDTO save(NhaCungCapDTO item);
    NhaCungCapDTO delete(Long id);
    NhaCungCapDTO update(NhaCungCapDTO item);
}
