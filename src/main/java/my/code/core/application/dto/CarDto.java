package my.code.core.application.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import my.code.core.database.entity.Status;
import my.code.core.domain.model.CartItemModel;

import java.math.BigDecimal;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarDto {
    Long id;
    String make;
    String model;
    BigDecimal price;
    Status status;
    List<CartItemModel> cartItems;
}
