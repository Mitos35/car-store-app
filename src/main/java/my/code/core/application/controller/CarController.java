package my.code.core.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.CarDto;
import my.code.core.application.dto.CarRequestDto;
import my.code.core.application.facade.CarFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Vehicle operations in a car store", description = "Operations for working with Cars")
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarFacade carFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getAllCars() {
        return carFacade.getAllCars();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto getCarById(@PathVariable Long id) {
        return carFacade.getCarById(id);
    }

    @GetMapping("/search")
    public List<CarDto> searchCars(@RequestParam String model) {
        return carFacade.searchCars(model);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@RequestBody CarRequestDto carRequestDto) {
        return carFacade.saveCar(carRequestDto);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarDto updateCar(@PathVariable() Long id, @RequestBody CarDto carDto) {
        return carFacade.updateCar(carDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carFacade.deleteCar(id);
    }

}
