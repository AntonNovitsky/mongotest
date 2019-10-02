package by.novitsky.mongotest.service;

import by.novitsky.mongotest.dao.UserRepository;
import by.novitsky.mongotest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getUserByName(String name){
        return userRepository.findByFirstName(name);
    }

    public List<User> getUserByLastName(String name){
        return userRepository.findByLastName(name);
    }

}
