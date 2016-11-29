package com.isometric.entity;

import org.springframework.data.annotation.Id;

public class Bid {

    @Id
    private String bidId;
    private String userId;
    private String postId;
    private double bidAmount;

    public Bid(String bidId, String userId, String postId, double bidAmount) {
        this.bidId = bidId;
        this.userId = userId;
        this.postId = postId;
        this.bidAmount = bidAmount;
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

}
