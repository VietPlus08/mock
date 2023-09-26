package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {

    public List<NhaCungCap> findByStatusTrue();
}
