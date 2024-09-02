package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import uz.library.takm.dto.UserDto;
import uz.library.takm.model.Role;
import uz.library.takm.model.RoleName;
import uz.library.takm.model.User;
import uz.library.takm.repository.RoleRepository;
import uz.library.takm.repository.UserRepository;
import uz.library.takm.util.PasswordUtils;

@Service // Аннотация, указывающая, что это компонент сервиса в Spring
public class UserService implements UserDetailsService {

    // Внедрение зависимостей через конструктор
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Метод для подсчета количества пользователей в базе данных
    public long countUsers() {
        return userRepository.count();
    }

    @Transactional
    public void changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found")); // Проверка на существование пользователя

        String hashedPassword = PasswordUtils.hashPassword(newPassword); // Хэширование нового пароля
        user.setPassword(hashedPassword);

        userRepository.save(user); // Сохранение пользователя с новым паролем
    }

    @Override // Переопределение метода из UserDetailsService для загрузки пользователя по логину
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + login));

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getLogin())
            .password(user.getPassword())
            .authorities(new SimpleGrantedAuthority(user.getRole().getRoleName().name())) // Присваивание роли пользователю
            .build();
    }

    // Метод для поиска пользователя по логину
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
            .orElseThrow(() -> new RuntimeException("User not found with login: " + login));
    }

    // Метод для регистрации нового пользователя
    public User register(UserDto userDto) {
        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists"); // Проверка на уникальность логина
        }

        if (userDto.getPassword().length() < 8 || !userDto.getPassword().matches(".*\\d.*")) {
            throw new RuntimeException("Пароль не удовлетворяет условиям! Введите минимум 8 символов и 1 цифру");
        }

        User user = new User(); // Создание нового объекта пользователя
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Шифрование пароля
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPatronymic(userDto.getPatronymic());
        user.setCardId(userDto.getCardId());

        Role userRole = roleRepository.findByRoleName(RoleName.User)
            .orElseThrow(() -> new RuntimeException("Role not found")); // Проверка на существование роли
        user.setRole(userRole);

        return userRepository.save(user); // Сохранение нового пользователя в базе данных
    }
}
