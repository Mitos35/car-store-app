package my.code.core.database.repository;

import my.code.core.database.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByModelContainingIgnoreCase(String model);
}
