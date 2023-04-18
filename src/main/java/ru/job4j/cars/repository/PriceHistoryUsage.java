package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.model.User;

import java.util.List;

public class PriceHistoryUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            var crudRepository = new CrudRepository(sf);
            var priceHistoryRepository = new PriceHistoryRepository(crudRepository);
            var postRepository = new PostRepository(crudRepository);
            var userRepository = new UserRepository(crudRepository);
            var user = new User();
            user.setLogin("admin4");
            user.setPassword("password");
            userRepository.create(user);
            var post = new Post();
            post.setDescription("description");
            post.setUser(user);
            postRepository.create(post);
            var priceHistory1 = new PriceHistory();
            var priceHistory2 = new PriceHistory();
            priceHistory1.setBefore(500);
            priceHistory1.setAfter(400);
            priceHistory2.setBefore(700);
            priceHistory2.setAfter(650);
            post.setPriceHistoryList(List.of(priceHistory1, priceHistory2));
            postRepository.update(post);
            priceHistoryRepository.findById(priceHistory1.getId())
                    .ifPresent(System.out::println);
            priceHistoryRepository.findAllOrderById()
                    .forEach(System.out::println);
            priceHistoryRepository.findByPostId(post.getId())
                    .forEach(System.out::println);
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}