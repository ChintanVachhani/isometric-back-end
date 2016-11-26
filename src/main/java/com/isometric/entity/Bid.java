package com.isometric.entity;

import org.springframework.data.annotation.Id;

public class Bid {

    @Id
    private String bidId;
    private String userId;
    private String postId;
    private double bidAmount;
    private String bidQuantity;

    public Bid(String bidId, String userId, String postId, double bidAmount, String bidQuantity) {
        this.bidId = bidId;
        this.userId = userId;
        this.postId = postId;
        this.bidAmount = bidAmount;
        this.bidQuantity = bidQuantity;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(String bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

}
