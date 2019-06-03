package com.group.call.almundo.response;

import java.io.Serializable;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
public class ErrorResponse implements Serializable {

    private String code;
    private String message;

    public ErrorResponse() {

    }

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
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
}
