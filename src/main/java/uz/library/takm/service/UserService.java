package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.library.takm.model.User;
import uz.library.takm.repository.UserRepository;
import uz.library.takm.util.PasswordUtils;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Метод для получения количества пользователей
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
}
