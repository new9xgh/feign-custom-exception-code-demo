package tech.xproject.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.xproject.common.core.constant.ServiceNameConstant;
import tech.xproject.order.config.FeignErrorDecoder;
import tech.xproject.order.pojo.dto.CreateOrderReqDTO;
import tech.xproject.order.pojo.entity.Order;

/**
 * @author kent
 */
@FeignClient(name = ServiceNameConstant.ORDER_SERVICE, configuration = {FeignErrorDecoder.class})
public interface RemoteOrderService {

    /**
     * 创建订单
     *
     * @param createOrderReqDTO createOrderReqDTO
     * @return Order
     */
    @PostMapping("/order/create")
    Order create(@RequestBody CreateOrderReqDTO createOrderReqDTO);
}
