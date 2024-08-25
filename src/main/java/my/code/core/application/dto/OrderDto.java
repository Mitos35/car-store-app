package my.code.core.application.dto;

import lombok.Data;
import my.code.core.database.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private List<CartItemDto> cartItems;
    private OrderStatus status;
}
