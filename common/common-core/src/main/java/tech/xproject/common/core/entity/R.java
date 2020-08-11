package tech.xproject.common.core.entity;

import lombok.Getter;
import lombok.Setter;
import tech.xproject.common.core.enums.ResultCodeEnum;
import tech.xproject.common.core.util.JsonUtil;

import java.io.Serializable;

public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private T data;

    private R(int code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public R() {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
        this.data = null;
    }

    public boolean succeed() {
        return ResultCodeEnum.SUCCESS.getCode() == this.code;
    }

    public boolean unSucceed() {
        return !this.succeed();
    }

    public static <T> R<T> success() {
        return new R<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), null);
    }

    public static <T> R<T> success(String message) {
        return new R<>(ResultCodeEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> R<T> success(T data) {
        return new R<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> R<T> success(String message, T data) {
        return new R<>(ResultCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> R<T> error() {
        return new R<>(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMessage(), null);
    }

    public static <T> R<T> error(String message) {
        return new R<>(ResultCodeEnum.ERROR.getCode(), message, null);
    }

    public static <T> R<T> error(ResultCodeEnum resultCodeEnum) {
        return new R<>(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null);
    }

    public static <T> R<T> error(ResultCodeEnum resultCodeEnum, String message) {
        if (message == null) {
            message = resultCodeEnum.getMessage();
        }
        return new R<>(resultCodeEnum.getCode(), message, null);
    }

    public static <T> R<T> error(int code, String message) {
        return new R<>(code, message, null);
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONStringWithNull(this);
    }
}
