package com.isometric.entity;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

import java.awt.*;

public class Post {

    @Id
    private String postId;
    private String postTitle;
    private String postDescription;
    private String userId;
    private Binary itemFile;
    private String requestTime;
    private String itemMaterial;
    private String itemSize;
    private String itemBuiltType;
    private String itemColorType;

    public Post(String postId, String postTitle, String postDescription, String userId, Binary itemFile, String requestTime, String itemMaterial, String itemSize, String itemBuiltType, String itemColorType) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.userId = userId;
        this.itemFile = itemFile;
        this.requestTime = requestTime;
        this.itemMaterial = itemMaterial;
        this.itemSize = itemSize;
        this.itemBuiltType = itemBuiltType;
        this.itemColorType = itemColorType;
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

    public Binary getItemFile() {
        return itemFile;
    }

    public void setItemFile(Binary itemFile) {
        this.itemFile = itemFile;
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
