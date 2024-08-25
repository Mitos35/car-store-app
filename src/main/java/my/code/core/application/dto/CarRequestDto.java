package my.code.core.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import my.code.core.database.entity.Status;
import my.code.core.domain.model.CartItemModel;

import java.math.BigDecimal;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarRequestDto {

    @Schema(description = "Car make make", example = "Toyota")
    @NotBlank
    String make;

    @Schema(description = "Car model make", example = "Camry")
    @NotBlank
    String model;

    @Schema(description = "Car price", example = "24000")
    @NotNull
    BigDecimal price;

    Status status = Status.AVAILABLE;
}
