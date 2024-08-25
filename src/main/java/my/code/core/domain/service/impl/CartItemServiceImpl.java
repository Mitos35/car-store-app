package my.code.core.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.CartItemRequestDto;
import my.code.core.database.entity.Car;
import my.code.core.database.entity.CartItem;
import my.code.core.database.entity.Order;
import my.code.core.database.entity.User;
import my.code.core.database.repository.CarRepository;
import my.code.core.database.repository.CartItemRepository;
import my.code.core.database.repository.OrderRepository;
import my.code.core.database.repository.UserRepository;
import my.code.core.domain.model.CartItemModel;
import my.code.core.domain.service.CartItemService;
import my.code.core.mapper.CartItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional
    public CartItemModel addCartItem(CartItemRequestDto cartItemRequestDto) {
        CartItem item = new CartItem();

        Car car = carRepository.findById(cartItemRequestDto.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        User user = userRepository.findById(cartItemRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        item.setCar(car);
        item.setUser(user);
        item.setQuantity(cartItemRequestDto.getQuantity());

        item = cartItemRepository.save(item);
        return cartItemMapper.toModelFromEntity(item);

    }

    @Override
    public List<CartItemModel> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(cartItemMapper::toModelFromEntity)
                .toList();
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    @Transactional
    public CartItemModel updateCartItemQuantity(Long cartItemId, int quantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));
        item.setQuantity(quantity);
        item = cartItemRepository.save(item);
        return cartItemMapper.toModelFromEntity(item);
    }

    @Override
    @Transactional
    public void checkout(Long userId, Long orderId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        for (CartItem cartItem : cartItems) {
            cartItem.setOrder(order);
            cartItemRepository.save(cartItem);
        }

        BigDecimal totalAmount = cartItems.stream()
                .map(cartItem -> cartItem.getCar().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);
        orderRepository.save(order);

        cartItemRepository.deleteAll(cartItems);
    }
}
