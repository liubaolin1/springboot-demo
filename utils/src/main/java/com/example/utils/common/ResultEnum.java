package com.example.utils.common;

/**
 * @Description: 返回code
 * @author: liubao
 * @Date: Created in 2018/7/3 18:50
 */
public enum ResultEnum {

    OK(200, "成功"),

    NO_TOKEN(310, "未登录"),

    BAD_REQUEST(400, "错误请求"),

    FORBIDDEN(403, "服务拒绝执行"),

    INTERNAL_ERROR(500, "系统错误，请联系管理员"),

    NOT_SUPPORTED(505, "非法连接"),

    PAGE_NOT_FOUND(404, "页面未找到"),

    UNKNOWN(-1, "未知错误"),

    BAD_PARAM_REQUEST(601, "参数错误请求"),

    FAILE(9999, "系统错误"),

    CAPTCHA_FAIL(9998, "验证码错误"),

    LOGIN_FALL(9997, "用户名或密码错误"),

    WAP_LOGIN_CAPTCHA(9996, "用户名或密码错误"),

    THIRD_PARTY_FAILE(9000, "第三方接口错误"),

    DATA_NO_EXISTS(9995, "数据记录不存在");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResultEnum() {
    }


}
