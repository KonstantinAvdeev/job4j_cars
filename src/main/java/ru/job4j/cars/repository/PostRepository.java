package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    public Post create(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }

    public void update(Post post) {
        crudRepository.run(session -> session.merge(post));
    }

    public void delete(int postId) {
        crudRepository.run(
                "delete from Post where id = :fId",
                Map.of("fId", postId)
        );
    }

    public List<Post> findAllOrderById() {
        return crudRepository.query("from Post order by id asc", Post.class);
    }

    public Optional<Post> findById(int postId) {
        return crudRepository.optional(
                "from Post where id = :fId", Post.class,
                Map.of("fId", postId)
        );
    }

    public List<Post> findByUserId(int userId) {
        return crudRepository.query(
                "from Post where auto_user_id = :userId", Post.class,
                Map.of("userId", userId)
        );
    }

    public List<Post> findByCarBrand(String carBrand) {
        return crudRepository.query(
                "from Post p JOIN FETCH p.car where p.car.name = :carBrand", Post.class,
                Map.of("carBrand", carBrand)
        );
    }

    public List<Post> findPostWithPhotos() {
        return crudRepository.query("from Post p JOIN FETCH p.files WHERE "
                + "size(files) > 0", Post.class);
    }

    public List<Post> findLastDayPosts() {
        LocalDateTime lastDay = LocalDateTime.now().minusDays(1);
        return crudRepository.query("from Post p WHERE p.created >= :lastDay",
                Post.class, Map.of("lastDay", lastDay));
    }

}
