package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.library.takm.dto.UserDto;
import uz.library.takm.model.Role;
import uz.library.takm.model.RoleName;
import uz.library.takm.model.User;
import uz.library.takm.repository.RoleRepository;
import uz.library.takm.repository.UserRepository;
import uz.library.takm.util.PasswordUtils;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public User authenticate(String login, String password) {
        User user = userRepository.findByLogin(login)
            .orElseThrow(() -> new RuntimeException("User not found"));

        String hashedPassword = PasswordUtils.hashPassword(password);
        if (!hashedPassword.equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User register(UserDto userDto) {
        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(PasswordUtils.hashPassword(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPatronymic(userDto.getPatronymic());
        user.setCardId(userDto.getCardId());

        // Установить роль по умолчанию
        Role userRole = roleRepository.findByRoleName(RoleName.User)
            .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(userRole);

        return userRepository.save(user);
    }
}
