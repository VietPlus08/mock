package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.entity.Product;
import fa.com.mock_back_end.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000/")
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public void init() {
        productService.save(new Product(1, "IPhone X", 10000, "6 inches", "CPU","url","Dien thoai iphone"));
        productService.save(new Product(2, "IPhone XS", 20000, "6 inches", "CPU","url","Dien thoai iphone"));
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> showList() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ResponseEntity<Product> findItem(@RequestParam("id") Integer id) {
        Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Product> createItem(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateItem(@RequestParam("id") Integer id,
                           @Valid @RequestBody Product productForm) {
        Product product = productService.findById(id);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        productService.update(product, productForm);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteItem(@RequestParam("id") Integer id) {
        boolean isProductExist = productService.delete(id);
        if (isProductExist) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
