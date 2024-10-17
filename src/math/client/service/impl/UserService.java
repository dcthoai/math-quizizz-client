package math.client.service.impl;

import com.google.gson.Gson;
import math.client.connection.ConnectionUtils;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.BaseResponse;
import math.client.model.User;
import math.client.service.IUserService;

/**
 *
 * @author dctho
 */
public class UserService implements IUserService {
    
    private ConnectionUtils connection;

    public UserService() {
        try {
            connection = new ConnectionUtils();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean login(String username, String password) {
        try {
            Gson gson = new Gson();
            User user = new User(username, password);
            BaseRequest baseRequest = new BaseRequest("/user/login", gson.toJson(user));

            connection.sendData(gson.toJson(baseRequest));
            String response = connection.receiveData();
            BaseResponse baseResponse = gson.fromJson(response, BaseResponse.class);

            return baseResponse.getStatus();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception");
        }
        
        return false;
    }

    @Override
    public boolean logout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
