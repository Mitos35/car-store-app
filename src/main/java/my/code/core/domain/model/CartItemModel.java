package my.code.core.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CartItemModel {
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
