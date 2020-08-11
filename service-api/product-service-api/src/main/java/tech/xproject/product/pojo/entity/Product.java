package tech.xproject.product.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author kent
 */
@Data
public class Product {

    private Long id;

    private String name;

    private Long stock;
    
    private Date created;

}
