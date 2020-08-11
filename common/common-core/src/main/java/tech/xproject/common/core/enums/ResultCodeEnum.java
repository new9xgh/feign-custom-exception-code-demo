package tech.xproject.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author kent
 */

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    /**
     * base
     */
    SUCCESS(0, "success"),
    ERROR(-1, "network error"),
    ERROR_PARAMETER(1000, "params error"),
    /**
     * auth
     */
    ERROR_UNAUTHENTICATED(4001, "unauthenticated"),
    /**
     * product
     */
    ERROR_PRODUCT_NOT_EXIST(6001, "product not exist"),
    ERROR_NOT_ENOUGH_STOCK(6002, "not enough stock"),
    /**
     * microservices
     */
    ERROR_MICROSERVICES(5000, "microservices error"),
    ;

    /**
     * code
     */
    private int code;
    /**
     * message
     */
    private String message;
}
