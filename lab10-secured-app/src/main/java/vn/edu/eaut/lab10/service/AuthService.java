package vn.edu.eaut.lab10.service;

import vn.edu.eaut.lab10.model.User;
import vn.edu.eaut.lab10.repository.UserRepository;

public class AuthService {
    private final UserRepository userRepository = new UserRepository();

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.isActive())
            return null;
        if (!user.getPassword().equals(password))
            return null;
        return user;
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}