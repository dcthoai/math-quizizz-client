package math.client.service;

/**
 *
 * @author dctho
 */
public interface IUserService {

    boolean register(String username, String password);
    boolean login(String username, String password);
    boolean logout();
}
