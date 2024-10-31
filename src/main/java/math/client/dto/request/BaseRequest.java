package math.client.dto.request;

import math.client.common.Constants;

import java.util.UUID;

@SuppressWarnings("unused")
public class BaseRequest {

    private String endPoint, request, action;

    public BaseRequest() {}

    public BaseRequest(String endPoint) {
        this.endPoint = endPoint;
        this.request = Constants.NO_BODY;
        this.action = UUID.randomUUID().toString();
    }

    public BaseRequest(String endPoint, String request) {
        this.endPoint = endPoint;
        this.request = request;
        this.action = UUID.randomUUID().toString();
    }

    public BaseRequest(String endPoint, String request, String action) {
        this.endPoint = endPoint;
        this.request = request;
        this.action = action;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
