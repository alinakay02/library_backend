package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.UserDto;
import uz.library.takm.model.User;
import uz.library.takm.repository.UserRepository;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/lib/getUser")
    public User getUser(@RequestParam String login) {
        return userRepository.findUserByLogin(login);
    }

    @GetMapping("/lib/getAllUser")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @PostMapping("/lib/saveUser")
    public User saveUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return user;
    }

}
