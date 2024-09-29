package math.client.service;

/**
 *
 * @author dctho
 */
public interface UserService {
    boolean login(String username, String password);
    boolean logout();
}
