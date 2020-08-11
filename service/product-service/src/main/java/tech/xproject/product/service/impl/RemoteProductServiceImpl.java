package tech.xproject.product.service.impl;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import tech.xproject.common.core.enums.ResultCodeEnum;
import tech.xproject.common.core.exception.BusinessException;
import tech.xproject.common.core.util.JsonUtil;
import tech.xproject.product.feign.RemoteProductService;
import tech.xproject.product.pojo.dto.DeductStockReqDTO;
import tech.xproject.product.pojo.entity.Product;

/**
 * @author kent
 */
@Slf4j
@RestController
public class RemoteProductServiceImpl implements RemoteProductService {

    @Override
    public Boolean deductStock(DeductStockReqDTO deductStockReqDTO) {
        log.info("deduct stock params: " + JsonUtil.toJSONStringWithNull(deductStockReqDTO));
        if(!testProductMap().containsKey(deductStockReqDTO.getProductId())){
            throw new BusinessException(ResultCodeEnum.ERROR_NOT_ENOUGH_STOCK);
        }
        Product product = testProductMap().get(deductStockReqDTO.getProductId());
        if (product.getStock() < deductStockReqDTO.getStock()) {
            log.error("deduct stock error: " + ResultCodeEnum.ERROR_NOT_ENOUGH_STOCK.getMessage());
            throw new BusinessException(ResultCodeEnum.ERROR_NOT_ENOUGH_STOCK);
        }
        log.info("deduct stock success");
        return true;
    }

    /**
     * 测试产品数据
     *
     * @return Map<Long, Product>
     */
    private Map<Long, Product> testProductMap() {
        Map<Long, Product> productMap = new HashMap<>(2);
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("测试产品1");
        product1.setStock(100L);
        product1.setCreated(new Date());

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("测试产品2");
        product2.setStock(0L);
        product2.setCreated(new Date());

        productMap.put(product1.getId(), product1);
        productMap.put(product2.getId(), product2);
        return productMap;
    }

}
