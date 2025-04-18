package com.ctse.microservices.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long id;

    @JsonProperty("orderNumber")
    private String orderNumber;

    @JsonProperty("skuCode")
    private String skuCode;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("quantity")
    private Integer quantity;

    @Embedded
    private UserDetails userDetails;


    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
    public String getSkuCode() {
        return skuCode;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public UserDetails getUserDetails() {
        return userDetails;
    }

}
