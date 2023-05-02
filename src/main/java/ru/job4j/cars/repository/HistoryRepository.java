package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.History;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class HistoryRepository {
    private final CrudRepository crudRepository;
    public List<History> findAllByOwnerId(int id) {
        return crudRepository.query(
                "from History AS h JOIN FETCH h.owner where owner_id = :fId", History.class,
                Map.of("fId", id)
        );
    }

}