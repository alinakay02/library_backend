package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.UserDto;
import uz.library.takm.model.User;
import uz.library.takm.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/count")
    public long getUserCount() {
        return userService.countUsers();
    }

    @PutMapping("/{userId}/changePassword")
    public void changePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        userService.changePassword(userId, newPassword);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/authenticate")
    public User authenticate(@RequestParam String login, @RequestParam String password, HttpSession session) {
        User user = userService.authenticate(login, password);

        // Установка атрибута сессии для роли
        session.setAttribute("userId", user.getId());
        session.setAttribute("login", user.getLogin());
        session.setAttribute("role", user.getRole().getRoleName().name());

        return user;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    // Метод для получения пользователя из сессии
    private User getUserFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new RuntimeException("Not authenticated");
        }

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("Not authenticated");
        }

        return userService.getUserById(userId);
    }
}