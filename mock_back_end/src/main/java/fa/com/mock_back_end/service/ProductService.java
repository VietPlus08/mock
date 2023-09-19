package fa.com.mock_back_end.service;

import fa.com.mock_back_end.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Integer id);
    boolean delete(Integer id);
    Product save(Product product);
    void update(Product product, Product productForm);
}
