package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Метод для получения количества пользователей
    @GetMapping("/count")
    public long getUserCount() {
        return userService.countUsers();
    }

    // Метод для смены пароля
    @PutMapping("/{userId}/changePassword")
    public void changePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        userService.changePassword(userId, newPassword);
    }
}
