package com.example.utils.common;

import java.io.Serializable;

/**
 * @Description: 统一返回结果类
 * @author: liubao
 * @Date: Created in 2018/7/3 17:16
 */
public class ActionResult<T> implements Serializable {
    private static final long serialVersionUID = 1772055640075900959L;

    private int code;
    private String message;
    private T data;

    public ActionResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ActionResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    @Override
    public String toString() {
        return "ActionResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
