package com.github.framework.evo.common.model;

import java.io.Serializable;

/**
 * Created by sino on 2019/9/9.
 */
public class ResponseVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    public static final int BUSY = -100;
    public static final String SUCCESS_TEXT = "Success";
    public static final String FAIL_TEXT = "Fail";
    public static final String BUSY_TEXT = "Busy";

    private int status;
    private String statusText;
    private T data;

    public ResponseVo() {
        this.status = SUCCESS;
        this.statusText = SUCCESS_TEXT;
    }

    public ResponseVo(int status, String statusText, T data) {
        this.status = status;
        this.statusText = statusText;
        this.data = data;
    }

    public ResponseVo(T data) {
        if(data instanceof Exception) {
            Exception ex = (Exception)data;
            this.status = FAIL;
            this.statusText = ex.getLocalizedMessage();
        } else {
            this.status = SUCCESS;
            this.statusText = SUCCESS_TEXT;
            this.data = data;
        }
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
        if(status == SUCCESS) {
            this.statusText = SUCCESS_TEXT;
        } else if(status == BUSY) {
            this.statusText = BUSY_TEXT;
        } else {
            this.statusText = "";
        }
    }

    public String getStatusText() {
        return this.statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <M> ResponseVo<M> ok() {
        return new ResponseVo();
    }

    public static <T> ResponseVo<T> ok(T data) {
        return new ResponseVo(data);
    }

}
