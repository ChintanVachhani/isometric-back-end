package com.isometric.entity;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class ID {
    @Id
    private String id;
    private BigInteger userId;
    private BigInteger postId;
    private BigInteger bidId;
    private BigInteger shoppingCartId;

    public ID(String id, BigInteger userId, BigInteger postId, BigInteger bidId, BigInteger shoppingCartId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.bidId = bidId;
        this.shoppingCartId = shoppingCartId;
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

    public BigInteger getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(BigInteger shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

}
