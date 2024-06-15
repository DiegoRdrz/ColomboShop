package com.BackEnd.Colomboshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.BackEnd.Colomboshop.Model.ShoppingCart;
import com.BackEnd.Colomboshop.Repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public Optional<ShoppingCart> getShoppingCartById(String id) {
        return shoppingCartRepository.findById(id);
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart updateShoppingCart(String id, ShoppingCart shoppingCart) {
        shoppingCart.setShoppingCartID(id); // Asegurar que el ID esté establecido correctamente
        return shoppingCartRepository.save(shoppingCart);
    }

    public void deleteShoppingCart(String id) {
        shoppingCartRepository.deleteById(id);
    }
}

