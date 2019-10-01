package by.novitsky.mongotest.dao;

import by.novitsky.mongotest.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public List<User> findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);
}
