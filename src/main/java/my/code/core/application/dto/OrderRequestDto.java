package my.code.core.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import my.code.core.database.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private Long userId;

    private LocalDateTime orderDate;

    private BigDecimal totalAmount;

    private List<CartItemDto> cartItems;

    @Schema(description = "Order status", example = "PENDING")
    @NotNull
    private OrderStatus status;
}
