package com.chandni.service;

import com.chandni.api.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    /*
    Connection to a db; for now just a local in-memory hashmap/list
     */
    private List<User> users;

    public UserService(){
        this.users = new ArrayList<>();

        User user1 = new User(1, "Alby", 32, "alby@abc.com");
        User user2 = new User(1, "Molly", 25, "molly@abc.com");
        User user3 = new User(1, "Shae", 42, "shae@abc.com");

        users.addAll(Arrays.asList(user1, user2, user3));

    }

    public User getUser(String name) {
        for (User user: this.users){
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        User newUser = new User(user.getId(), user.getName(), user.getAge(), user.getEmail());
        this.users.add(newUser);
    }

    public List<User> getAllUsers() {
        return this.users;
    }

    public User updateUser(User user) {
        for (ListIterator<User> iter = this.users.listIterator(); iter.hasNext(); ) {
            User existingUser = iter.next();
            if (existingUser.getName().equalsIgnoreCase(user.getName())) {
                iter.set(user);
                return user;
            }
        }
        return null;
    }

    public boolean deleteUser(String username) {
        return this.users.removeIf(existingUser -> existingUser.getName().equalsIgnoreCase(username));
    }
}
