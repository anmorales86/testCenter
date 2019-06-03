package com.group.call.almundo.response;

import java.io.Serializable;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
public class ResponseCall implements Serializable {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
