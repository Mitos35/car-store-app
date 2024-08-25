package my.code.core.mapper;

import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.CarDto;
import my.code.core.application.dto.CarRequestDto;
import my.code.core.database.entity.Car;
import my.code.core.domain.model.CarModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CarMapper {
    private final ModelMapper mapper;

    public CarModel toModelFromEntity(Car car) {
        return Objects.isNull(car) ? null : mapper.map(car, CarModel.class);
    }

    public Car toEntityFromRequest(CarRequestDto carRequestDto) {
        return Objects.isNull(carRequestDto) ? null : mapper.map(carRequestDto, Car.class);
    }

    public CarDto toDtoFromModel(CarModel carModel) {
        return Objects.isNull(carModel) ? null : mapper.map(carModel, CarDto.class);
    }


    public void updateCarFromDto(CarDto carDto, Car car) {
        mapper.map(carDto, car);
    }
}
