package my.code.database.repository;

import my.code.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByEmail(String email);

    List<User> findAllByOrderByFirstNameAsc();

    List<User> findAllByOrderByAgeDesc();

    User getById(Long id);

}
