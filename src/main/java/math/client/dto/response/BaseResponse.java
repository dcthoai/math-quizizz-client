package math.client.dto.response;

import math.client.common.Constants;

@SuppressWarnings("unused")
public class BaseResponse {

    private Integer code;
    private Boolean status;
    private String message;
    private String action;
    private String result;

    public BaseResponse() {}

    public BaseResponse(String action, String result) {
        this.code = Constants.SUCCESS;
        this.status = true;
        this.message = "Success";
        this.action = action;
        this.result = result;
    }

    public BaseResponse(Integer code, Boolean status) {
        this.code = code;
        this.status = status;
    }

    public BaseResponse(Integer code, Boolean status, String action) {
        this.code = code;
        this.status = status;
        this.action = action;
    }

    public BaseResponse(Integer code, Boolean status, String action, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.action = action;
    }

    public BaseResponse(Integer code, Boolean status, String action, String message, String result) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.action = action;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
