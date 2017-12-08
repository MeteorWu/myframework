package com.framework.meteor.framework.exception;

import com.framework.meteor.framework.constant.ResultMsg;



public class ApiException extends ServiceException {
    protected ResultMsg resultMsg = ResultMsg.SYSTEM_ERROR;

    public ApiException() {
        super();
    }

    public ApiException(ResultMsg resultMsg) {
        super();
        this.resultMsg = resultMsg;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return resultMsg.getCode();
    }

    public ResultMsg getResultMsg() {
        return this.resultMsg;
    }

    @Override
    public String getMessage() {
        return resultMsg.getMsg();
    }
}
