package my.code.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.code.core.database.entity.Status;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarModel {
    private Long id;
    private String make;
    private String model;
    private BigDecimal price;
    private Status status;
    private List<CartItemModel> cartItems;
}
