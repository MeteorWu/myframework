package com.framework.meteor.framework.constant;

import lombok.Getter;
import lombok.Setter;

public enum ResultMsg {

    SUCCESS("SUCCESS", "操作成功完成"),
    /********************************************** 模块通用 **********************************************************/
    FAULT_ACCESS_TOKEN("10001", "您没有权限访问该接口"),
    NO_ACCESS_TOKEN("10002", "缺少访问授权参数"),
    PARAMETER_EMPTY("10003", "参数不能为空"),
    PARAMETER_INCORRECT("10004", "参数错误"),
    USER_VERIFICATION_CODE_INCORRECT("10005", "验证码错误"),
    PHONE_ERROR("10006", "手机参数错误"),
    USERDETAILS_NOT_EXIST("10007", "该用户没有认证权限"),
    AUTH_FAIL("10008", "认证失败"),
    PARAMETER_NOT_ENOUGH("10009", "必填项请求参数不完整，请检查"),
    PAGE_FLIP_PARAMETER_ERROR("10010", "请输入正确的分页参数"),
    NOT_DELETE_ADMIN("10011","老板不能删除"),
    START_TIME_IS_NULL("10012", "请设置查询条件：开始时间"),
    ORDER_HAVE_FINISH("100104", "订单已经完成不能删除"),
    SIGN_ERROR("10105", "签名错误"),
    VERSION_ERROR("99999", "软件版本太低，请升级软件"),

    /********************************************** http请求的系统code **********************************************************/
    RESPONSE_PARAMS_ERROR("000100", "被请求方返回参数错误"),
    RESPONSE_ERROR("000101", "被请求方服务器访问异常"),// 500
    RESPONSE_FAIL("000102", "被请求方服务器访问异常"),// != 200

    SYSTEM_ERROR("30000", "系统异常"),

    INTERNAL_SERVER_ERROR("500", "请求的服务产生异常"),

    NULL_POINT_ERROR("2001", "空指针异常"),

    SQL_ERROR("2002", "SQL执行异常"),

    OTHER_ERROR("9001", "其他异常"),



    NO_TOKEN("10101", "缺少参数[tokenId]"),

    FAULT_TOKEN("10102", "错误的Token"),

    EXPIRE_TOKEN("10103", "Token已过期，请重新登录"),

    USER_ALREADY_EXIST("10201", "该手机号已注册"),

    USER_PASSWORD_INCORRECT("10206", "密码错误"),

    USER_FAULT_PHONE("10211", "错误的手机号"),

    PERMISSION_FORBIDDEN("10300", "无权操作");


    @Setter
    @Getter
    private String code;

    @Setter
    @Getter
    private String msg;
    private ResultMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
