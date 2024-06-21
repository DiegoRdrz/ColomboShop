package com.BackEnd.Colomboshop.Service;

import com.BackEnd.Colomboshop.Model.Order;
import com.BackEnd.Colomboshop.Model.User;
import com.BackEnd.Colomboshop.Repository.OrderRepository;
import com.BackEnd.Colomboshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            order.setUserID(user.getId());
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Order updateOrder(String id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setOrderID(id);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
