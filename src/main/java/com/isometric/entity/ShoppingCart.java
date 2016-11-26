package com.isometric.entity;

import org.springframework.data.annotation.Id;

public class ShoppingCart {

    @Id
    private String shoppingCartId;
    private String postId;
    private String bidId;
    private String quantity;
    private String price;

    public ShoppingCart(String postId, String bidId, String quantity, String price) {
        this.postId = postId;
        this.bidId = bidId;
        this.quantity = quantity;
        this.price = price;
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

}
