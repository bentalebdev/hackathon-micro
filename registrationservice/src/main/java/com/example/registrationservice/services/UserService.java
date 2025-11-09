package com.example.registrationservice.services;

import lombok.RequiredArgsConstructor;
import com.example.registrationservice.model.User;
import org.springframework.stereotype.Service;
import com.example.registrationservice.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user){
        User updatedUser = userRepository.findById(id).orElse(null);

        if(updatedUser != null){
            updatedUser.setEmail(user.getEmail());
            updatedUser.setGroups(user.getGroups());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setSpecialite(user.getSpecialite());
        }

        return userRepository.save(updatedUser);
    }
}