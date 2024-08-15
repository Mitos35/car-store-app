package my.code.database.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long id;

    private User user;

    private List<CartItem> cartItems = new ArrayList<>();

    private double totalAmount;
}
