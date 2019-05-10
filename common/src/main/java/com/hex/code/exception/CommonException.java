package com.hex.code.exception;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.00:21
 */
public class CommonException extends RuntimeException {


    public CommonException() {

    }

    public CommonException(String msg) {
        super(msg);
    }

    public CommonException(Throwable throwable) {
        super(throwable);
    }

    public CommonException(Throwable throwable, String msg) {
        super(throwable);
    }
}
