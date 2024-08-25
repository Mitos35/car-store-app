package my.code.core.domain.service;

import my.code.core.application.dto.CarDto;
import my.code.core.application.dto.CarRequestDto;
import my.code.core.domain.model.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<CarModel> getAllCars();

    Optional<CarModel> getCarById(Long id);

    CarModel saveCar(CarRequestDto carRequestDto);

    CarModel updateCar(CarDto carDto);

    void deleteCar(Long id);

    List<CarModel> searchCars(String query);
}
