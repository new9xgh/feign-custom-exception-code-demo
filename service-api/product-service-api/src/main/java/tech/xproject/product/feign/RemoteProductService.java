package tech.xproject.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.xproject.common.core.constant.ServiceNameConstant;
import tech.xproject.product.config.FeignErrorDecoder;
import tech.xproject.product.pojo.dto.DeductStockReqDTO;

/**
 * @author kent
 */
@FeignClient(name = ServiceNameConstant.PRODUCT_SERVICE, configuration = {FeignErrorDecoder.class})
public interface RemoteProductService {

    /**
     * 扣减库存
     *
     * @param deductStockReqDTO createOrderReqDTO
     * @return Boolean
     */
    @PostMapping("/product/deduct-stock")
    Boolean deductStock(@RequestBody DeductStockReqDTO deductStockReqDTO);
}
