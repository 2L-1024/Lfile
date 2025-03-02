package org.liaolong.base.api;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ll
 * @since 2025-03-02 10:58
 */
@Setter
@Getter
public class ApiResponse<T> {
    private int code;

    private String msg;

    private T data;

    public ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(0, "success", data);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(0, "success", null);
    }
}
