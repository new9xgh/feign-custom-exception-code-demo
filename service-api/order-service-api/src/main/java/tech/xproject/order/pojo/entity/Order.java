package tech.xproject.order.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author kent
 */
@Data
public class Order {

    private String orderNo;

    private Long userId;

    private Long productId;

    private Date created;
    
}
