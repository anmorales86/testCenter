package com.group.call.almundo.controller;

import com.group.call.almundo.business.Dispatcher;
import com.group.call.almundo.constants.ConstantErrorCode;
import com.group.call.almundo.exceptions.CallCenterException;
import com.group.call.almundo.request.RequestCall;
import com.group.call.almundo.response.ErrorResponse;
import com.group.call.almundo.response.ResponseCall;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
@RestController
@RequestMapping("/almundo")
public class CallController {

    private static Log LOGGER = LogFactory.getLog(CallController.class);

    @Autowired
    Dispatcher dispatcher;

    /**
     * Service to process a call
     * @param requestCall
     * @return ResponseCall
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @RequestMapping(value = "/receiveCall",
            method = RequestMethod.POST,
            produces="application/json",
            consumes="application/json")
    public @ResponseBody
    ResponseCall receiveCall(@RequestBody RequestCall requestCall) throws CallCenterException {
        LOGGER.info("INFO!!! CallController - receiveCall()");
        return dispatcher.receiveCall(requestCall);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorResponse handleException(Exception ex)
    {
        LOGGER.error("ERROR!!! CallController - handleException()");
        ErrorResponse errorResponse = new ErrorResponse();
        String code = (ex instanceof CallCenterException)
                ? ((CallCenterException) ex ).getCode()
                : String.valueOf(ConstantErrorCode.ERROR_CODE_BASE);
        errorResponse.setCode(code);
        errorResponse.setMessage(ex.getMessage());

        return errorResponse;
    }

}
