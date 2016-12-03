package com.isometric.entity;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class ID {
    @Id
    private String id;
    private BigInteger userId;
    private BigInteger postId;
    private BigInteger bidId;
    private BigInteger orderId;

    public ID(String id, BigInteger userId, BigInteger postId, BigInteger bidId, BigInteger orderId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.bidId = bidId;
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

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

}
