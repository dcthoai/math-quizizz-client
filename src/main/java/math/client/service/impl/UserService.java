package math.client.service.impl;

import com.google.gson.Gson;
import math.client.dto.request.UserRequest;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.BaseResponse;
import math.client.service.IUserService;
import math.client.service.utils.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dctho
 */
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final ConnectionUtil connection;

    public UserService() {
        connection = ConnectionUtil.getInstance();
    }

    @Override
    public boolean register(String username, String password) {
        Gson gson = new Gson();
        UserRequest user = new UserRequest(username, password);
        BaseRequest request = new BaseRequest("/api/user/register", gson.toJson(user));

        connection.sendData(request);
        BaseResponse<?> response = connection.receiveData();

        return response.getStatus();
    }

    @Override
    public boolean login(String username, String password) {
        Gson gson = new Gson();
        UserRequest user = new UserRequest(username, password);
        BaseRequest request = new BaseRequest("/api/user/login", gson.toJson(user));

        connection.sendData(request);
        BaseResponse<?> response = connection.receiveData();

        return response.getStatus();
    }

    @Override
    public boolean logout() {
        BaseRequest request = new BaseRequest("/api/user/logout");
        connection.sendData(request);
        BaseResponse<?> response = connection.receiveData();

        return response.getStatus();
    }
}
