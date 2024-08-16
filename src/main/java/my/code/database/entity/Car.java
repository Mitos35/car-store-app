package my.code.database.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private Long id;

    private String make;

    private String model;

    private double price;

    private Status status = Status.AVAILABLE;
}
