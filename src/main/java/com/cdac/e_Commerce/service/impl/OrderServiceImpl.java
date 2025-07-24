package com.cdac.e_Commerce.service.impl;

import com.cdac.e_Commerce.service.OrderService;
import com.cdac.e_Commerce.dto.OrderDto;
import com.cdac.e_Commerce.dto.OrderItemDto;
import com.cdac.e_Commerce.model.Order;
import com.cdac.e_Commerce.model.OrderItem;
import com.cdac.e_Commerce.repository.OrderRepository;
import com.cdac.e_Commerce.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    private OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setItems(order.getItems() != null ? order.getItems().stream().map(this::toDto).collect(Collectors.toList()) : null);
        return dto;
    }

    private OrderItemDto toDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setProductId(item.getProductId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }

    private Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setUserId(dto.getUserId());
        order.setStatus(dto.getStatus());
        order.setTotalAmount(dto.getTotalAmount());
        if (dto.getItems() != null) {
            List<OrderItem> items = dto.getItems().stream().map(this::toEntity).collect(Collectors.toList());
            items.forEach(i -> i.setOrder(order));
            order.setItems(items);
        }
        return order;
    }

    private OrderItem toEntity(OrderItemDto dto) {
        OrderItem item = new OrderItem();
        item.setProductId(dto.getProductId());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());
        return item;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::toDto).orElse(null);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto placeOrder(OrderDto orderDto) {
        Order order = toEntity(orderDto);
        order.setStatus(order.getStatus() != null ? order.getStatus() : "PLACED");
        Order saved = orderRepository.save(order);
        return toDto(saved);
    }

    @Override
    public OrderDto updateOrderStatus(Long id, String status) {
        Optional<Order> existing = orderRepository.findById(id);
        if (existing.isPresent()) {
            Order order = existing.get();
            order.setStatus(status);
            Order saved = orderRepository.save(order);
            return toDto(saved);
        }
        return null;
    }
} 