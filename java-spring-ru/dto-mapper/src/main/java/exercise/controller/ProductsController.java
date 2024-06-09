package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping("")
    public List<ProductDTO> index() {
        return productRepository
            .findAll()
            .stream()
            .map(productMapper::map)
            .toList();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO productCreateDTO) {
        Product product = productMapper.map(productCreateDTO);
        return productMapper.map(productRepository.save(product));
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable long id) {
        return productRepository
            .findById(id)
            .map(productMapper::map)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@PathVariable long id, @RequestBody ProductUpdateDTO productUpdateDTO) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productMapper.update(productUpdateDTO, product);
        return productMapper.map(productRepository.save(product));
    }
    // END
}
