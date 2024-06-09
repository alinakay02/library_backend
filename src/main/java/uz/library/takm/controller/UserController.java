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

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
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
    public ResponseEntity<?> authenticate(@RequestBody UserLoginDto userLoginDto) {
        try {
            // Попытка аутентификации
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getLogin(), userLoginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Генерация JWT токена
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtUtil.generateToken(userDetails);

            // Получение дополнительных данных пользователя
            User user = userService.findByLogin(userLoginDto.getLogin());
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("role", user.getRole().getRoleName().name());
            response.put("userId", user.getId());

            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с таким логином не существует");
        } catch (BadCredentialsException e) {
            System.out.println("обработка ошибки");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный пароль");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка связи с сервером");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        try {
            User user = userService.register(userDto);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            // Возвращаем клиенту статус ошибки с сообщением из исключения
            return ResponseEntity
                .badRequest()
                .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}