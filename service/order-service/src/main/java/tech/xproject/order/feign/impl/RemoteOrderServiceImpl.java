package tech.xproject.order.feign.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import tech.xproject.common.core.util.JsonUtil;
import tech.xproject.order.feign.RemoteOrderService;
import tech.xproject.order.pojo.dto.CreateOrderReqDTO;
import tech.xproject.order.pojo.entity.Order;
import tech.xproject.product.feign.RemoteProductService;
import tech.xproject.product.pojo.dto.DeductStockReqDTO;

import java.util.Date;
import java.util.UUID;

/**
 * @author kent
 */
@Slf4j
@RestController
@AllArgsConstructor
public class RemoteOrderServiceImpl implements RemoteOrderService {

    private final RemoteProductService remoteProductService;

    @Override
    public Order create(CreateOrderReqDTO createOrderReqDTO) {
        log.info("create order params: " + JsonUtil.toJSONStringWithNull(createOrderReqDTO));
        DeductStockReqDTO deductStockReqDTO = new DeductStockReqDTO();
        deductStockReqDTO.setProductId(createOrderReqDTO.getProductId());
        deductStockReqDTO.setStock(createOrderReqDTO.getNum());
        remoteProductService.deductStock(deductStockReqDTO);

        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setUserId(createOrderReqDTO.getUserId());
        order.setProductId(createOrderReqDTO.getProductId());
        order.setCreated(new Date());
        log.info("create order success, data: " + JsonUtil.toJSONStringWithNull(order));
        return order;
    }
}


