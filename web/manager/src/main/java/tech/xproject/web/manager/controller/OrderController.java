package tech.xproject.web.manager.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.xproject.common.core.entity.R;
import tech.xproject.common.core.util.JsonUtil;
import tech.xproject.order.feign.RemoteOrderService;
import tech.xproject.order.pojo.dto.CreateOrderReqDTO;
import tech.xproject.order.pojo.entity.Order;

import javax.validation.Valid;

/**
 * @author kent
 */
@RestController
@RequestMapping("/order")
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final RemoteOrderService remoteOrderService;

    /**
     * 创建订单
     *
     * @return R
     */
    @PostMapping("/create")
    public R<?> create(@Valid @RequestBody CreateOrderReqDTO createOrderReqDTO) {
        log.info("create order params: " + JsonUtil.toJSONStringWithNull(createOrderReqDTO));
        Order order = remoteOrderService.create(createOrderReqDTO);
        log.info("create order success, data: " + JsonUtil.toJSONStringWithNull(order));
        return R.success(order);
    }
}
