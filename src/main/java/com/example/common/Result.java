package com.example.common;

import com.example.service.UserService;

public class Result<T>{
    private String status;
    private String Message;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(String status, String message, T data) {
        this.status = status;
        this.Message = message;
        this.data = data;
    }

}
