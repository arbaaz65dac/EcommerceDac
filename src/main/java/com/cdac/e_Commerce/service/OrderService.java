package com.cdac.e_Commerce.service;

import com.cdac.e_Commerce.dto.OrderDto;
import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    List<OrderDto> getOrdersByUserId(Long userId);
    OrderDto placeOrder(OrderDto orderDto);
    OrderDto updateOrderStatus(Long id, String status);
} 