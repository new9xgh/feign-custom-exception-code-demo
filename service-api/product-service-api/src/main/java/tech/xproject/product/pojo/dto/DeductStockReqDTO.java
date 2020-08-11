package tech.xproject.product.pojo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author kent
 */
@Data
public class DeductStockReqDTO {

    @NotNull(message = "productId required")
    private Long productId;

    @NotNull
    @Min(1)
    private Long stock;
}
