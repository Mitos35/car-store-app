package my.code.core.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.CartItemDto;
import my.code.core.application.dto.CartItemRequestDto;
import my.code.core.application.facade.CartItemFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cart Items", description = "Operations related to cart items, including adding, updating, and removing items from the cart, and performing checkout.")
@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CarItemController {
    private final CartItemFacade cartItemFacade;

    @PostMapping
    public ResponseEntity<CartItemDto> addCartItem(@RequestBody CartItemRequestDto cartItemRequestDto) {
        CartItemDto cartItemDto = cartItemFacade.addCartItem(cartItemRequestDto);
        return new ResponseEntity<>(cartItemDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItemDto>> getCartItemsByUserId(@PathVariable Long userId) {
        List<CartItemDto> cartItems = cartItemFacade.getCartItemsByUserId(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId) {
        cartItemFacade.deleteCartItemById(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{cartItemId}/quantity")
    public ResponseEntity<CartItemDto> updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam int quantity) {
        CartItemDto updatedCartItem = cartItemFacade.updateCartItemQuantity(cartItemId, quantity);
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkout(@RequestParam Long userId, @RequestParam Long orderId) {
        cartItemFacade.checkout(userId, orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
