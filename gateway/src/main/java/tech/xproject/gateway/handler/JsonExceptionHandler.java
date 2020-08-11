package tech.xproject.gateway.handler;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import tech.xproject.common.core.enums.ResultCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kent
 */
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {

    public JsonExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * get error attributes
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Throwable error = super.getError(request);
        String exMessage = error != null ? error.getMessage() : ResultCodeEnum.ERROR.getMessage();
        String message = String.format("request error [%s %s]，exception：%s", request.methodName(), request.uri(), exMessage);

        Map<String, Object> map = new HashMap<>(3);
        map.put("code", ResultCodeEnum.ERROR.getCode());
        map.put("message", message);
        map.put("data", null);
        return map;
    }

    /**
     * render with json
     *
     * @param errorAttributes
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * response http code 200
     * the error code need use the code in response content
     *
     * @param errorAttributes
     */
    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return HttpStatus.OK.value();
    }
}
