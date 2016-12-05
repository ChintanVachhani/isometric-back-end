package com.isometric.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;

public class Post implements Serializable {

    @Id
    private BigInteger postId;
    private String postTitle;
    private String postDescription;
    private BigInteger userId;
    private String postTime;
    private String itemMaterial;
    private String itemSize;
    private String itemBuiltType;
    private String itemColorType;

    public Post(BigInteger postId, String postTitle, String postDescription, BigInteger userId, String postTime, String itemMaterial, String itemSize, String itemBuiltType, String itemColorType) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.userId = userId;
        this.postTime = postTime;
        this.itemMaterial = itemMaterial;
        this.itemSize = itemSize;
        this.itemBuiltType = itemBuiltType;
        this.itemColorType = itemColorType;
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

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getpostTime() {
        return postTime;
    }

    public void setpostTime(String postTime) {
        this.postTime = postTime;
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

    public String getItemBuiltType() {
        return itemBuiltType;
    }

    public void setItemBuiltType(String itemBuiltType) {
        this.itemBuiltType = itemBuiltType;
    }

    public String getItemColorType() {
        return itemColorType;
    }

    public void setItemColorType(String itemColorType) {
        this.itemColorType = itemColorType;
    }
}
