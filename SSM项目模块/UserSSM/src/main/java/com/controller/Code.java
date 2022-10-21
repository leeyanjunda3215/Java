package com.controller;

public class Code {
    /**
     * SAVE_ok：  11   增-成功     SAVE_err   10 增-失败
     * DELETE_ok  21   减-成功    DELETE_err  20 减-失败
     * UPDATE_ok  31   更新-成功   UPDATE_err 30 更新-失败
     * GET_ok     41  查询成功    GET_err     40 查询-成功
     */
    public static final Integer SAVE_ok = 20011;
    public static final Integer DELETE_ok = 20021;
    public static final Integer UPDATE_ok = 20031;
    public static final Integer GET_ok = 20041;

    public static final Integer SAVE_err = 20010;
    public static final Integer DELETE_err = 20020;
    public static final Integer UPDATE_err = 20030;
    public static final Integer GET_err = 20040;
}
