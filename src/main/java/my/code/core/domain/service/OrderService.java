package my.code.core.domain.service;

import my.code.core.application.dto.OrderRequestDto;
import my.code.core.database.entity.OrderStatus;
import my.code.core.domain.model.OrderModel;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderModel> getAllOrders();

    Optional<OrderModel> getOrderById(Long id);

    OrderModel createOrder(OrderRequestDto orderRequestDto);

    OrderModel updateOrder(OrderRequestDto orderRequestDto);

    void deleteOrder(Long id);

    List<OrderModel> getOrdersByUserId(Long userId);

    OrderModel updateOrderStatus(Long orderId, OrderStatus status);
}
