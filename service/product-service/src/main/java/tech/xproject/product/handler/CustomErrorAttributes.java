package tech.xproject.product.handler;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import tech.xproject.common.core.exception.BusinessException;

import java.util.Map;

/**
 * @author kent
 */
@Component
@Primary
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Throwable error = this.getError(webRequest);
        if (error instanceof BusinessException) {
            errorAttributes.put("code", ((BusinessException) error).getCode());
        }
        return errorAttributes;
    }
}