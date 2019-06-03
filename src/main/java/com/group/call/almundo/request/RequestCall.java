package com.group.call.almundo.request;

import java.io.Serializable;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
public class RequestCall implements Serializable {

    private String nameComplete;

    public RequestCall() {

    }

    public String getNameComplete() {
        return nameComplete;
    }

    public void setNameComplete(String nameComplete) {
        this.nameComplete = nameComplete;
    }
}
