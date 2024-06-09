package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public long countUsers() {
        return userRepository.count();
    }

    @Transactional
    public void changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        String hashedPassword = PasswordUtils.hashPassword(newPassword);
        user.setPassword(hashedPassword);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + login));

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getLogin())
            .password(user.getPassword())
            .authorities(new SimpleGrantedAuthority(user.getRole().getRoleName().name()))
            .build();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
            .orElseThrow(() -> new RuntimeException("User not found with login: " + login));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User register(UserDto userDto) {
        /*if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }*/
        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        if (userDto.getPassword().length() < 8 || !userDto.getPassword().matches(".*\\d.*")) {
            throw new RuntimeException("Пароль не удовлетворяет условиям! Введите минимум 8 символов и 1 цифру");
        }

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPatronymic(userDto.getPatronymic());
        user.setCardId(userDto.getCardId());

        Role userRole = roleRepository.findByRoleName(RoleName.User)
            .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(userRole);

        return userRepository.save(user);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
