package tech.xproject.web.manager.handler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.xproject.common.core.entity.R;
import tech.xproject.common.core.enums.ResultCodeEnum;
import tech.xproject.common.core.exception.BusinessException;

import java.util.List;


/**
 * @author kent
 */
@Slf4j
@RestControllerAdvice
public class WebGlobalExceptionHandler {

    @ExceptionHandler({FeignException.class})
    @ResponseBody
    public R<?> feignExceptionHandler(FeignException exception) {
        log.error(exception.getMessage(), exception);
        return R.error(exception.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public R<?> runtimeExceptionHandler(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return R.error(exception.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public R<?> exceptionHandler(Exception exception) {
        log.error(exception.getMessage(), exception);
        return R.error(exception.getMessage());
    }

    @ExceptionHandler({BusinessException.class})
    public R<?> businessExceptionHandler(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return R.error(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    public R<?> bindExceptionHandler(BindException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.get(0).getDefaultMessage();
        log.error(errorMessage, exception);
        return R.error(ResultCodeEnum.ERROR_PARAMETER.getCode(), errorMessage);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R<?> validateExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.get(0).getDefaultMessage();
        log.error(errorMessage, exception);
        return R.error(ResultCodeEnum.ERROR_PARAMETER.getCode(), errorMessage);
    }
}
