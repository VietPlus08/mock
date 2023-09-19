package fa.com.mock_back_end.service;

import fa.com.mock_back_end.entity.Product;
import fa.com.mock_back_end.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Integer id) {
        Product product = findById(id);
        if (product != null) {
            productRepository.delete(findById(id));
            return true;
        }
        return false;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void update(Product product, Product productForm) {
        product.setProductName(product.getProductName());
        product.setCpu(product.getCpu());
        product.setCost(product.getCost());
        product.setScreenSize(product.getScreenSize());
        product.setUrlImage(product.getUrlImage());
        product.setDescription(product.getDescription());
    }
}
