package com.BackEnd.Colomboshop.Service;

import com.BackEnd.Colomboshop.Model.Order;
import com.BackEnd.Colomboshop.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
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
