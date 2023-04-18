package com.apache.kafka.CustomSerializer;

import java.io.Serializable;

public class Order implements Serializable {

    String customerName;
    String product;
    int quantity;

    public Order() {

    }

    public Order(String customerName, String product, int quantity) {
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
