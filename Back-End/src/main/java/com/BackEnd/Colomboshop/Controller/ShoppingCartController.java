package com.BackEnd.Colomboshop.Controller;
import com.BackEnd.Colomboshop.DTO.ShoppingCartDTO;
import com.BackEnd.Colomboshop.Model.ShoppingCart;
import com.BackEnd.Colomboshop.Service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colomboShop/user/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ShoppingCartDTO> getAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllShoppingCarts();
        return shoppingCarts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable String id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.getShoppingCartById(id);
        return shoppingCart.map(cart -> ResponseEntity.ok(convertToDto(cart)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> createShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = convertToEntity(shoppingCartDTO);
        ShoppingCart createdShoppingCart = shoppingCartService.createShoppingCart(shoppingCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(createdShoppingCart));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> updateShoppingCart(@PathVariable String id, @RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = convertToEntity(shoppingCartDTO);
        ShoppingCart updatedShoppingCart = shoppingCartService.updateShoppingCart(id, shoppingCart);
        return ResponseEntity.ok(convertToDto(updatedShoppingCart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable String id) {
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.noContent().build();
    }

    private ShoppingCartDTO convertToDto(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        BeanUtils.copyProperties(shoppingCart, shoppingCartDTO);
        return shoppingCartDTO;
    }

    private ShoppingCart convertToEntity(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        return shoppingCart;
    }
}

