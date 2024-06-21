package com.BackEnd.Colomboshop.Controller;

import com.BackEnd.Colomboshop.DTO.ProductDTO;
import com.BackEnd.Colomboshop.Model.Product;
import com.BackEnd.Colomboshop.Service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
 @RequestMapping("/colomboShop/seller/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return convertToDto(product);
    }

    @GetMapping("/category/{categoryID}")
    public List<Product> getProductsByCategory(@PathVariable String categoryID) {
        return productService.getProductsByCategory(categoryID);
    }

    @GetMapping("/search/{name}")
    public List<Product> searchProductsByName(@RequestParam String name) {
        return productService.filterProductsByString(name);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productService.getProductsByPriceRange(minPrice, maxPrice);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) throws Exception {
        Product product = convertToEntity(productDTO);
        Product createdProduct = productService.createProduct(product);
        return convertToDto(createdProduct);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product updatedProduct = productService.updateProduct(id, product);
        return convertToDto(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    private ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}

