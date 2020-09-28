package com.ppd.GPSTrackerBackend.service;

import com.ppd.GPSTrackerBackend.model.User;
import com.ppd.GPSTrackerBackend.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * @param user
     * @return User
     * This method will save an user in database using crudRepository API features
     */
    public User addNewUser(User user){
        return userRepository.save(user);
    }

    /**
     * @param email
     * @param password
     * @return User
     * This method will find an existing user by his email and password fields using crudRepository API features
     * and will return the founded user
     */
    public User getUser(String email,  String password){
        User user = userRepository.findByEmailAndPassword(email, password);
        return user;
    }

    /**
     * @param id
     * @return User
     * This method will find an existing user informations by his id field using crudRepository API features
     * and will return the founded user
     */
    public User getUserInfoById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    /**
     * @param u
     * @return User
     * This method will update an existing user in database using crudRepository API features
     * and will return the updated user
     */
    public User updateUser(User u){
        return userRepository.save(u);
    }

    /**
     * @return Iterable<User> : List of Users
     * This method will return a list of all existing Users in database using crudRepository API features
     */
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * @param id : unique identifier of an user
     * This method will delete an existing User in database by his unique identifier and using crudRepository API features
     */
    public void deleteVehicle(Integer id) {
        userRepository.deleteById(id);
    }
}
