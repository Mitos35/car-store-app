package my.code.core.application.facade;

import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.CartItemDto;
import my.code.core.application.dto.CartItemRequestDto;
import my.code.core.domain.service.CartItemService;
import my.code.core.mapper.CartItemMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartItemFacade {
    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;

    public CartItemDto addCartItem(CartItemRequestDto cartItemRequestDto) {
        return cartItemMapper.toDtoFromModel(cartItemService.addCartItem(cartItemRequestDto));
    }

    public List<CartItemDto> getCartItemsByUserId(Long userId) {
        return cartItemService.getCartItemsByUserId(userId)
                .stream()
                .map(cartItemMapper::toDtoFromModel)
                .toList();
    }

    public void deleteCartItemById(Long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
    }

    public CartItemDto updateCartItemQuantity(Long cartItemId, int quantity) {
        return cartItemMapper.toDtoFromModel(cartItemService.updateCartItemQuantity(cartItemId, quantity));
    }

    public void checkout(Long userId, Long orderId) {
        cartItemService.checkout(userId, orderId);
    }
}
