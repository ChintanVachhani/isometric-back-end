package com.isometric.entity;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class Order {

    @Id
    private BigInteger orderId;
    private BigInteger userId;
    private BigInteger postId;
    private BigInteger bidId;
    private int quantity;
    private double amount;
    private boolean isCheckedOut;

    public Order(BigInteger orderId, BigInteger userId, BigInteger postId, BigInteger bidId, int quantity, double amount, boolean isCheckedOut) {
        this.orderId = orderId;
        this.userId = userId;
        this.postId = postId;
        this.bidId = bidId;
        this.quantity = quantity;
        this.amount = amount;
        this.isCheckedOut = isCheckedOut;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getPostId() {
        return postId;
    }

    public void setPostId(BigInteger postId) {
        this.postId = postId;
    }

    public BigInteger getBidId() {
        return bidId;
    }

    public void setBidId(BigInteger bidId) {
        this.bidId = bidId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }
}
