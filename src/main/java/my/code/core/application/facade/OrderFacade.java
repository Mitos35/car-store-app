package my.code.core.application.facade;

import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.OrderDto;
import my.code.core.application.dto.OrderRequestDto;
import my.code.core.application.exceptions.NotFoundException;
import my.code.core.database.entity.OrderStatus;
import my.code.core.domain.service.OrderService;
import my.code.core.mapper.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public List<OrderDto> getOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(orderMapper::toDtoFromModel)
                .toList();

    }

    public List<OrderDto> getOrdersByUserId(Long userId) {
        return orderService.getOrdersByUserId(userId)
                .stream()
                .map(orderMapper::toDtoFromModel)
                .toList();
    }

    public OrderDto getOrderById(Long id) {
        return orderService.getOrderById(id)
                .map(orderMapper::toDtoFromModel)
                .orElseThrow(() -> new NotFoundException("Order with id {0} not found", id));
    }

    public OrderDto createOrder(OrderRequestDto orderRequestDto) {
        return orderMapper.toDtoFromModel(orderService.createOrder(orderRequestDto));
    }

    public OrderDto updateOrder(OrderRequestDto orderRequestDto) {
        return orderMapper.toDtoFromModel(orderService.updateOrder(orderRequestDto));
    }

    public void deleteOrder(Long id) {
        orderService.deleteOrder(id);
    }


    public OrderDto updateOrderStatus(Long orderId, OrderStatus status) {
        return orderMapper.toDtoFromModel(orderService.updateOrderStatus(orderId, status));
    }
}
