package my.code.core.mapper;

import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.CartItemDto;
import my.code.core.database.entity.CartItem;
import my.code.core.domain.model.CartItemModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CartItemMapper {
    private final ModelMapper mapper;


    public CartItemModel toModelFromEntity(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        CartItemModel cartItemModel = mapper.map(cartItem, CartItemModel.class);

        if (cartItem.getCar() != null) {
            cartItemModel.setCarMake(cartItem.getCar().getMake());
            cartItemModel.setCarModel(cartItem.getCar().getModel());
            cartItemModel.setCarPrice(cartItem.getCar().getPrice());
            cartItemModel.setStatus(cartItem.getCar().getStatus().name());
        }

        return cartItemModel;
    }

    public CartItemDto toDtoFromModel(CartItemModel cartItemModel) {
        return Objects.isNull(cartItemModel) ? null : mapper.map(cartItemModel, CartItemDto.class);
    }

    public CartItem toEntityFromMDto(CartItemDto cartItemDto) {
        return Objects.isNull(cartItemDto) ? null : mapper.map(cartItemDto, CartItem.class);
    }
}
