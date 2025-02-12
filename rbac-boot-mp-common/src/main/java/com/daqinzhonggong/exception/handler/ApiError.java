package com.daqinzhonggong.exception.handler;

import lombok.Data;

/**
 * 用于封装API错误响应的数据结构
 *
 * @author free
 */
@Data
class ApiError {

    // 定义了一个私有成员变量status，用于表示HTTP状态码，默认值为400（Bad Request）。
    private Integer status = 400;
    // 定义了一个私有成员变量timestamp，用于记录错误发生的时间戳。
    private Long timestamp;
    // 定义了一个私有成员变量message，用于存储错误的详细信息。
    private String message;

    private ApiError() {
        timestamp = System.currentTimeMillis();
    }

    public static ApiError error(String message) {
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(Integer status, String message) {
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        return apiError;
    }

}


