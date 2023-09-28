package fa.com.mock_back_end.service;

import fa.com.mock_back_end.dto.NhaCungCapDTO;
import fa.com.mock_back_end.entity.NhaCungCap;

import java.util.List;
import java.util.Optional;

public interface NhaCungCapService {

    List<NhaCungCapDTO> findAllDTO();
    List<NhaCungCap> findAll();
    Optional<NhaCungCap> findById(Long id);
    NhaCungCap save(NhaCungCap item);
    NhaCungCap delete(Long id);
    NhaCungCap update(NhaCungCap item);
}
