package abstractions;

public interface IAuthService {
    boolean loginAsAdmin(String username, String password);
    boolean loginAsUser(String username, String password);
}
