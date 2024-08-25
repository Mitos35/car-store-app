package my.code.core.domain.service;

import my.code.core.application.dto.CartItemRequestDto;
import my.code.core.domain.model.CartItemModel;

import java.util.List;

public interface CartItemService {

    CartItemModel addCartItem(CartItemRequestDto cartItemRequestDto);

    List<CartItemModel> getCartItemsByUserId(Long userId);

    void removeCartItem(Long cartItemId);

    CartItemModel updateCartItemQuantity(Long cartItemId, int quantity);

    void checkout(Long userId, Long orderId);
}
