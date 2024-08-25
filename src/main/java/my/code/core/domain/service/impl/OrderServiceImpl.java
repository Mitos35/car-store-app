package my.code.core.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.OrderRequestDto;
import my.code.core.database.entity.Order;
import my.code.core.database.entity.OrderStatus;
import my.code.core.database.entity.User;
import my.code.core.database.repository.OrderRepository;
import my.code.core.database.repository.UserRepository;
import my.code.core.domain.model.OrderModel;
import my.code.core.domain.service.OrderService;
import my.code.core.mapper.CartItemMapper;
import my.code.core.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;
    private final OrderMapper orderMapper;


    @Override
    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toModelFromEntity)
                .toList();
    }

    @Override
    public Optional<OrderModel> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toModelFromEntity);
    }

    @Override
    @Transactional
    public OrderModel createOrder(OrderRequestDto orderRequestDto) {
        User user = userRepository.findById(orderRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(orderRequestDto.getOrderDate());
        order.setStatus(orderRequestDto.getStatus());

        order.setCartItems(orderRequestDto.getCartItems().stream()
                .map(cartItemMapper::toEntityFromMDto)
                .collect(Collectors.toList()));

        order.setTotalAmount(orderRequestDto.getTotalAmount());

        return orderMapper.toModelFromEntity(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderModel updateOrder(OrderRequestDto orderRequestDto) {
        Order order = orderRepository.findById(orderRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        order.setStatus(orderRequestDto.getStatus());
        order.setOrderDate(orderRequestDto.getOrderDate());

        return orderMapper.toModelFromEntity(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderModel> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(orderMapper::toModelFromEntity)
                .toList();
    }

    @Override
    @Transactional
    public OrderModel updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        order.setStatus(status);
        orderRepository.save(order);

        return orderMapper.toModelFromEntity(order);
    }
}
