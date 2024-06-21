package com.BackEnd.Colomboshop.Service;


import com.BackEnd.Colomboshop.Model.Category;
import com.BackEnd.Colomboshop.Model.Product;
import com.BackEnd.Colomboshop.Model.User;
import com.BackEnd.Colomboshop.Repository.CategoryRepository;
import com.BackEnd.Colomboshop.Repository.ProductRepository;
import com.BackEnd.Colomboshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if ("SELLER".equals(user.getRole()) || "ADMIN".equals(user.getRole())){
                product.setUserID(user.getId());
                Category category = categoryRepository.findById(product.getCategoryID())
                        .orElseThrow(() -> new Exception("La categoría especificada no existe"));
                product.setCategoryID(category.getCategoryID());
                return productRepository.save(product);
            } else {
                throw new RuntimeException("User is not authorized to create a product");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Product updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setProductID(id);
            return productRepository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> getProductsByCategory(String categoryID) {
        return productRepository.findByCategoryID(categoryID);
    }

    public List<Product> filterProductsByString(String name) {
        String regex = ".*" + name + ".*";
        return productRepository.findByNameProductRegex(regex);
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}

