package tech.xproject.order.pojo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author kent
 */
@Data
public class CreateOrderReqDTO {

    @NotNull(message = "userId required")
    private Long userId;

    @NotNull(message = "productId required")
    private Long productId;

    @NotNull(message = "num required")
    @Min(1)
    private Long num;
}
