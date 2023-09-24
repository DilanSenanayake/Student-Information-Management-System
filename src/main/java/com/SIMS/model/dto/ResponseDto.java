package com.SIMS.model.dto;

public class ResponseDto<T> {

    private String code;
    private String message;
    private T data;

    /**
     * Create a responseMessage.
     *
     * @param code HttpStatus code
     * @param msg  Custom message
     * @param obj  return data object if error the null
     */
    public ResponseDto(String code, String msg, T obj) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}



