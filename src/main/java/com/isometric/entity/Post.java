package com.isometric.entity;

import org.springframework.data.annotation.Id;

import java.awt.*;

public class Post {

    @Id
    private String postId;
    private String postTitle;
    private String postDescription;
    private String userId;
    private Image itemImage;
    private String requestTime;
    private String itemMaterial;
    private String itemSize;

    public Post(String postId, String postTitle, String postDescription, String userId, Image itemImage, String requestTime, String itemMaterial, String itemSize) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.userId = userId;
        this.itemImage = itemImage;
        this.requestTime = requestTime;
        this.itemMaterial = itemMaterial;
        this.itemSize = itemSize;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Image getItemImage() {
        return itemImage;
    }

    public void setItemImage(Image itemImage) {
        this.itemImage = itemImage;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getItemMaterial() {
        return itemMaterial;
    }

    public void setItemMaterial(String itemMaterial) {
        this.itemMaterial = itemMaterial;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

}
