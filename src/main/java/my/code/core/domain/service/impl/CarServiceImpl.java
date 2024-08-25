package my.code.core.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.CarDto;
import my.code.core.application.dto.CarRequestDto;
import my.code.core.database.entity.Car;
import my.code.core.database.repository.CarRepository;
import my.code.core.domain.model.CarModel;
import my.code.core.domain.service.CarService;
import my.code.core.mapper.CarMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarModel> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toModelFromEntity)
                .toList();
    }

    @Override
    public Optional<CarModel> getCarById(Long id) {
        return carRepository.findById(id)
                .map(carMapper::toModelFromEntity);
    }

    @Override
    @Transactional
    public CarModel saveCar(CarRequestDto carRequestDto) {
        Car car = carRepository.save(carMapper.toEntityFromRequest(carRequestDto));
        return carMapper.toModelFromEntity(car);
    }

    @Override
    @Transactional
    public CarModel updateCar(CarDto carDto) {
        return carRepository.findById(carDto.getId())
                .map(car -> {
                    carMapper.updateCarFromDto(carDto, car);
                    Car updatedCar = carRepository.save(car);
                    return carMapper.toModelFromEntity(updatedCar);
                }).orElseThrow(() -> new EntityNotFoundException("Car not found"));
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarModel> searchCars(String query) {
        return carRepository.findByModelContainingIgnoreCase(query)
                .stream()
                .map(carMapper::toModelFromEntity)
                .toList();
    }
}
