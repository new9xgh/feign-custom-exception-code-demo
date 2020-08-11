package tech.xproject.common.core.entity;

import lombok.Data;

/**
 * @author kent
 */
@Data
public class ExceptionInfo {

    private Long timestamp;

    private Integer status;

    private String exception;

    private String message;

    private String path;

    private String error;

    private Integer code;

    private String trace;
}