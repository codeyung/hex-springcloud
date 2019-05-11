package com.hex.code.order;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.23:36
 */
public class Result<T> {

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 失败
     */
    public static final int FAIL = 500;

//    /**
//     * 验证码错误
//     */
//    public static final int INVALID_VALIDATE_CODE_ERROR = 478;
//
//    /**
//     * 验证码过期错误
//     */
//    public static final int VALIDATE_CODE_EXPIRED_ERROR = 479;
//
//    /**
//     * 用户名不存在或密码错误
//     */
//    public static final int USERNAME_NOT_FOUND_OR_PASSWORD_ERROR = 400;
//
//    /**
//     * 当前操作没有权限
//     */
//    public static final int UNAUTHORIZED = 401;
//
//    /**
//     * 当前操作没有权限
//     */
//    public static final int NO_PERMISSION = 403;

    private String msg = "success";

    private int code = SUCCESS;

    /**
     * http 状态码
     */
    private int status = 200;

    private T data;

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public Result(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

    public Result(Throwable e, int code) {
        super();
        this.msg = e.getMessage();
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
