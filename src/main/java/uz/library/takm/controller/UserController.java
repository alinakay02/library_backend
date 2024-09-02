package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.UserDto;
import uz.library.takm.dto.UserLoginDto;
import uz.library.takm.model.User;
import uz.library.takm.service.UserService;
import uz.library.takm.util.JwtUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController // Аннотация для обозначения контроллера REST API
@RequestMapping("/users") // Базовый URL для всех методов в этом контроллере
public class UserController {

    // Внедрение зависимостей для сервисов и утилит
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired // Конструктор для автоматического внедрения зависимостей
    public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/count")
    public long getUserCount() {
        // Метод для получения количества пользователей
        return userService.countUsers();
    }

    @PutMapping("/{userId}/changePassword")
    public void changePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        // Метод для изменения пароля пользователя
        userService.changePassword(userId, newPassword);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserLoginDto userLoginDto) {
        try {
            // Аутентификация пользователя с помощью UsernamePasswordAuthenticationToken
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getLogin(), userLoginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication); // Установка контекста аутентификации

            // Генерация токена JWT для аутентифицированного пользователя
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtUtil.generateToken(userDetails);

            // Формирование ответа с JWT токеном и информацией о пользователе
            User user = userService.findByLogin(userLoginDto.getLogin());
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("role", user.getRole().getRoleName().name());
            response.put("userId", user.getId());

            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            // Обработка ошибки: пользователь не найден
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с таким логином не существует");
        } catch (BadCredentialsException e) {
            // Обработка ошибки: неверный пароль
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный пароль");
        } catch (Exception e) {
            // Обработка других ошибок сервера
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка связи с сервером");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        try {
            // Регистрация нового пользователя и возвращение информации о нем
            User user = userService.register(userDto);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            // В случае ошибки при регистрации, возвращается сообщение об ошибке
            return ResponseEntity
                .badRequest()
                .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
