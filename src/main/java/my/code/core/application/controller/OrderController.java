package my.code.core.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.OrderDto;
import my.code.core.application.dto.OrderRequestDto;
import my.code.core.application.facade.OrderFacade;
import my.code.core.database.entity.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order Management", description = "APIs for managing orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    @GetMapping()
    public ResponseEntity<List<OrderDto>> getOrderDtoList() {
        List<OrderDto> orders = orderFacade.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderDto> orders = orderFacade.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        OrderDto order = orderFacade.getOrderById(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderDto createdOrder = orderFacade.createOrder(orderRequestDto);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping()
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderDto updatedOrder = orderFacade.updateOrder(orderRequestDto);
        return ResponseEntity.ok(updatedOrder);
    }


    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatus status) {
        OrderDto updatedOrder = orderFacade.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }
}
