package math.client.dto.request;

@SuppressWarnings("unused")
public class UserRequest {

    private String username, password;

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
