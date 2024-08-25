package my.code.core.application.facade;

import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.CarDto;
import my.code.core.application.dto.CarRequestDto;
import my.code.core.application.exceptions.NotFoundException;
import my.code.core.database.entity.Car;
import my.code.core.domain.service.CarService;
import my.code.core.mapper.CarMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CarFacade {

    private final CarService carService;
    private final CarMapper carMapper;

    public List<CarDto> getAllCars() {
        return carService.getAllCars()
                .stream()
                .map(carMapper::toDtoFromModel)
                .toList();
    }

    public CarDto getCarById(Long id) {
        return carService.getCarById(id)
                .map(carMapper::toDtoFromModel)
                .orElseThrow(() -> new NotFoundException("Car with id {0} not found", id));
    }

    public CarDto saveCar(CarRequestDto carRequestDto) {
        return carMapper.toDtoFromModel(carService.saveCar(carRequestDto));
    }

    public CarDto updateCar(CarDto carDto) {
        return carMapper.toDtoFromModel(carService.updateCar(carDto));
    }

    public void deleteCar(Long id) {
        carService.deleteCar(id);
    }

    public List<CarDto> searchCars(String query) {
        return carService.searchCars(query)
                .stream()
                .map(carMapper::toDtoFromModel)
                .toList();
    }
}
