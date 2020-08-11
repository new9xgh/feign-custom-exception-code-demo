package tech.xproject.common.core.exception;

import lombok.Getter;
import tech.xproject.common.core.enums.ResultCodeEnum;

/**
 * @author kent
 */
public class BusinessException extends RuntimeException {

    @Getter
    private Integer code;

    @Getter
    private String message;

    public BusinessException() {
        this.code = ResultCodeEnum.ERROR.getCode();
        this.message = ResultCodeEnum.ERROR.getMessage();
    }

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public BusinessException(ResultCodeEnum resultCodeEnum, String message) {
        this.code = resultCodeEnum.getCode();
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
