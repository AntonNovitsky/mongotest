package by.novitsky.mongotest.dao;

import by.novitsky.mongotest.entity.AuthUser;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class AuthUserRepository {

    Set<AuthUser> users;

    public AuthUserRepository(){
        users = new HashSet<>();
        users.add(new AuthUser("admin","admin", "ROLE_ADMIN"));
        users.add(new AuthUser("user","user","ROLE_USER"));
    }


    public AuthUser findByName (String name){
        for(AuthUser user : users){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

}
