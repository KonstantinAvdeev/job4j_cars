package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository {
    private final CrudRepository crudRepository;

    public Car add(Car car) {
        crudRepository.run(session -> session.persist(car));
        return car;
    }

    public void update(Car car) {
        crudRepository.run(session -> session.merge(car));
    }

    public void delete(int id) {
        crudRepository.run(
                "delete from Car where id = :fId",
                Map.of("fId", id)
        );
    }

    public List<Car> findAll() {
        return crudRepository.query("from Car AS c JOIN FETCH c.owners "
                + "JOIN FETCH c.engine", Car.class);
    }

    public List<Car> findByOwnerId(int userId) {
        return crudRepository.query("from Car AS c JOIN FETCH c.owners JOIN FETCH c.engine "
                + "where user_id = :user_id", Car.class, Map.of("user_id", userId));
    }

    public Optional<Car> findById(int id) {
        return crudRepository.optional(
                "from Car AS c JOIN FETCH c.owners JOIN FETCH c.engine "
                        + "where c.id = :fId", Car.class,
                Map.of("fId", id)
        );
    }

}