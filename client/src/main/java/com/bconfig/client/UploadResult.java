package com.bconfig.client;

/**
 * Created by IntelliJ IDEA.
 *
 * @author gangwen.xu
 * Date  : 2018/6/12
 * Time  : 下午10:11
 * 类描述 :
 */
public class UploadResult {
    public static final UploadResult BAD_SERVICE_RESULT = new UploadResult(UploadReturnCode.BAD_SERVICE_CODE, "服务器异常");

    public static final UploadResult PARSE_RESPONSE_ERROR_RESULT = new UploadResult(UploadReturnCode.PARSE_RESPONSE_ERROR_CODE, "解析上传结果错误");

    public static final UploadResult TOKEN_INVALID_RESULT = new UploadResult(UploadReturnCode.INVALID_TOKEN_CODE, "无效的应用中心下发token");

    // code定义见UploadReturnCode类
    private final int code;

    private final String message;

    public UploadResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UploadResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
