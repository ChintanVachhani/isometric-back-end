package com.isometric.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by gfogl on 11/25/2016.
 */
public class ShoppingCart {

    @Id
    private String shoppingCartId;
    private String postId;
    private String bidId;
    private String quantity;
    private String price;
    private String lastLoginTime;

    public ShoppingCart() {
    }

    public ShoppingCart(String postId, String bidId, String quantity, String price, String lastLoginTime) {
        this.postId = postId;
        this.bidId = bidId;
        this.quantity = quantity;
        this.price = price;
        this.lastLoginTime = lastLoginTime;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "postId='" + postId + '\'' +
                ", bidId='" + bidId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }


}
