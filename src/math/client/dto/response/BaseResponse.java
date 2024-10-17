package math.client.dto.response;

public class BaseResponse<T> {

    private Integer code;
    private Boolean status;
    private String message;
    private T result;

    public BaseResponse() {}

    public BaseResponse(T result) {
        this.code = 200;
        this.status = true;
        this.message = "Success";
        this.result = result;
    }

    public BaseResponse(Integer code, Boolean status) {
        this.code = code;
        this.status = status;
    }

    public BaseResponse(Integer code, Boolean status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public BaseResponse(Integer code, Boolean status, String message, T result) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
