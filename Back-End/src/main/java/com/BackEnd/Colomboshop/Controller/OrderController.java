package com.BackEnd.Colomboshop.Controller;
import com.BackEnd.Colomboshop.DTO.OrderDTO;
import com.BackEnd.Colomboshop.Model.Order;
import com.BackEnd.Colomboshop.Service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colomboShop/user/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(createdOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(convertToDto(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    private OrderDTO convertToDto(Order order) {
        OrderDTO ordersDTO = new OrderDTO();
        BeanUtils.copyProperties(order, ordersDTO);
        return ordersDTO;
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        return order;
    }
}