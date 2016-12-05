package com.isometric.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;

public class Bid implements Serializable {

    @Id
    private BigInteger bidId;
    private BigInteger userId;
    private BigInteger postId;
    private String postTitle;
    private double bidAmount;

    public Bid(BigInteger bidId, BigInteger userId, BigInteger postId, String postTitle, double bidAmount) {
        this.bidId = bidId;
        this.userId = userId;
        this.postId = postId;
        this.postTitle = postTitle;
        this.bidAmount = bidAmount;
    }

    public BigInteger getBidId() {
        return bidId;
    }

    public void setBidId(BigInteger bidId) {
        this.bidId = bidId;
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

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

}
