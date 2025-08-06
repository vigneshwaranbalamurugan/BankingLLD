package services;

import abstractions.IAuthService;
import models.Admin;
import models.User;
import repository.UserRepository;

public class AuthService implements IAuthService {
    private final Admin admin;
    private final UserRepository userRepository;

    public AuthService(Admin admin, UserRepository userRepository) {
        this.admin = admin;
        this.userRepository = userRepository;
    }

    @Override
    public boolean loginAsAdmin(String username, String password) {
        return admin.getUserName().equals(username) &&
                admin.getPassword().equals(password);
    }

    @Override
    public boolean loginAsUser(String username, String password) {
        User user = userRepository.getUser(username);
        return user != null && user.getPassword().equals(password);
    }
}
