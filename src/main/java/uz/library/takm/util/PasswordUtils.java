package uz.library.takm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtils {

    private static final String FIXED_SALT = "EgG8UDlG9PEVk+ElaMQCedA=";

    // Хеширование пароля с использованием фиксированной соли
    public static String hashPassword(String password) {
        String saltedPassword = password + FIXED_SALT;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(saltedPassword.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
