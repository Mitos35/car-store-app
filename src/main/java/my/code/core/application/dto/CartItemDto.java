package my.code.core.application.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDto {
    Long id;
    Long carId;
    Long userId;
    Long orderId;
    int quantity;
    String carMake;
    String carModel;
    BigDecimal carPrice;
    String status;
}
