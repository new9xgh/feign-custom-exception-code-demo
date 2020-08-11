package tech.xproject.product.config;

import com.alibaba.fastjson.JSON;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import tech.xproject.common.core.entity.ExceptionInfo;
import tech.xproject.common.core.enums.ResultCodeEnum;
import tech.xproject.common.core.exception.BusinessException;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author kent
 */
@Slf4j
@Configuration
public class FeignErrorDecoder extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = super.decode(methodKey, response);

        if (exception instanceof RetryableException) {
            return exception;
        }

        try {
            if (exception instanceof FeignException && ((FeignException) exception).responseBody().isPresent()) {
                ByteBuffer responseBody = ((FeignException) exception).responseBody().get();
                String bodyText = StandardCharsets.UTF_8.newDecoder().decode(responseBody.asReadOnlyBuffer()).toString();
                ExceptionInfo exceptionInfo = JSON.parseObject(bodyText, ExceptionInfo.class);
                Integer code = Optional.ofNullable(exceptionInfo.getCode()).orElse(ResultCodeEnum.ERROR.getCode());
                String message = Optional.ofNullable(exceptionInfo.getMessage()).orElse(ResultCodeEnum.ERROR.getMessage());
                return new BusinessException(code, message);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return exception;
    }
}