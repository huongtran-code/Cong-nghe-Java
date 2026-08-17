package vn.edu.eaut.lab6.store;

import vn.edu.eaut.lab6.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserStore {
    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User("admin", "123456", "Quản Trị Viên Hệ Thống", "ADMIN"));
        users.add(new User("user", "123456", "Nguyễn Văn A (User)", "USER"));
        users.add(new User("tran_binh", "123456", "Trần Thị Bình (User)", "USER"));
        users.add(new User("le_cuong", "123456", "Lê Văn Cường (User)", "USER"));
    }

    public static List<User> findAll() {
        return users;
    }

    public static User findByUsername(String username) {
        if (username == null) return null;
        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    public static User authenticate(String username, String password) {
        User u = findByUsername(username);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    public static boolean updateRole(String username, String newRole) {
        User u = findByUsername(username);
        if (u != null && ("ADMIN".equalsIgnoreCase(newRole) || "USER".equalsIgnoreCase(newRole))) {
            u.setRole(newRole.toUpperCase());
            return true;
        }
        return false;
    }
}
