package fa.com.mock_back_end.service.impl;

import fa.com.mock_back_end.entity.NhaCungCap;
import fa.com.mock_back_end.repository.NhaCungCapRepository;
import fa.com.mock_back_end.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {

    @Autowired
    NhaCungCapRepository nhaCungCapRepository;

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
            return nhaCungCapRepository.save(nhaCungCap.get());
        }
        return null;
    }
}
