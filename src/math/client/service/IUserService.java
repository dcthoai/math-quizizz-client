package math.client.service;

/**
 *
 * @author dctho
 */
public interface IUserService {
    boolean login(String username, String password);
    boolean logout();
}
