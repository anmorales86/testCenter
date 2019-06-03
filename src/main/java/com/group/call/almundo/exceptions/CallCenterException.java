package com.group.call.almundo.exceptions;

import com.group.call.almundo.constants.ConstantErrorCode;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
public class CallCenterException extends Exception {

    public String getCode(){
        return ConstantErrorCode.ERROR_CODE_BASE;
    }

    public CallCenterException()
    {
    }

    public CallCenterException(String message)
    {
        super(message);
    }

    public CallCenterException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CallCenterException(Throwable cause)
    {
        super(cause);
    }

}
