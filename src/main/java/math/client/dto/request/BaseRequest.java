package math.client.dto.request;

public class BaseRequest {

    private String endPoint, request, action;

    public BaseRequest() {}

    public BaseRequest(String endPoint) {
        this.endPoint = endPoint;
    }

    public BaseRequest(String endPoint, String request) {
        this.endPoint = endPoint;
        this.request = request;
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
