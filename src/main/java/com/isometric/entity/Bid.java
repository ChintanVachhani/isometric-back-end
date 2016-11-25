package com.isometric.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by gfogl on 11/25/2016.
 */
public class Bid {

    @Id
    private String bidId;
    private String userId;
    private String postId;
    private String bidDescription;
    private String bidName;
    private String quantity;

    public Bid() {
    }

    public Bid(String userId, String postId, String bidDescription, String bidName, String quantity) {
        this.userId = userId;
        this.postId = postId;
        this.bidDescription = bidDescription;
        this.bidName = bidName;
        this.quantity = quantity;
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

    public String getBidDescription() {
        return bidDescription;
    }

    public void setBidDescription(String bidDescription) {
        this.bidDescription = bidDescription;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidNameName(String bidName) {
        this.bidName = bidName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                ", bidDescription='" + bidDescription + '\'' +
                ", bidName='" + bidName + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }


}
