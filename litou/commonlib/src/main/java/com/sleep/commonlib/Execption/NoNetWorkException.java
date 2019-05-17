package com.sleep.commonlib.Execption;

import java.io.IOException;

/**
 * Created by SleepYM09 on 2017/12/15.
 * <p>
 * blog: www.sleepym09.com
 */

public class NoNetWorkException extends IOException {

    public NoNetWorkException() {
    }

    public NoNetWorkException(String message) {
        super(message);
    }

}
