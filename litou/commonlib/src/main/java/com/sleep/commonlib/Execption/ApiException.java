package com.sleep.commonlib.Execption;


import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public class ApiException extends RuntimeException {
    private int mErrorCode;

    public ApiException(int mErrorCode) {
        this.mErrorCode = mErrorCode;

    }

    public ApiException(int mErrorCode, Object content) {
        this.mErrorCode = mErrorCode;
    }
    public ApiException(int mErrorCode, List<Object> content) {
        this.mErrorCode = mErrorCode;
    }
    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
    }



}
