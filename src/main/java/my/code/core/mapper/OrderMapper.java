package my.code.core.mapper;

import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.OrderDto;
import my.code.core.database.entity.Order;
import my.code.core.domain.model.OrderModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ModelMapper mapper;

    public OrderModel toModelFromEntity(Order order) {
        return Objects.isNull(order) ? null : mapper.map(order, OrderModel.class);
    }

    public OrderDto toDtoFromModel(OrderModel orderModel) {
        return Objects.isNull(orderModel) ? null : mapper.map(orderModel, OrderDto.class);
    }
}
