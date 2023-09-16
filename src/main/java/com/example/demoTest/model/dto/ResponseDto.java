package com.example.demoTest.model.dto;
import java.io.Serializable;

public class ResponseDto implements Serializable {

    private String code;
    private String message;
    private Object data;

    /**
     * Create a responseMessage.
     *
     * @param code HttpStatus code
     * @param msg  Custom message
     * @param obj  return data object if error the null
     */
    public ResponseDto(String code, String msg, Object obj) {
        this.code = code;
        this.message = msg;
        this.data = obj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringBuilder("ResponseDto{")
                .append("code='").append(code).append('\'')
                .append(", message='").append(message).append('\'')
                .append(", data=").append(data)
                .append('}')
                .toString();
    }
}



