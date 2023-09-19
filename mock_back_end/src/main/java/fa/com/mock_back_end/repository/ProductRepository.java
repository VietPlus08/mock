package fa.com.mock_back_end.repository;

import fa.com.mock_back_end.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
