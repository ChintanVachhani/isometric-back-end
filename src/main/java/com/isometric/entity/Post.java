package com.isometric.entity;

import org.springframework.data.annotation.Id;

import java.awt.*;

/**
 * Created by gfogl on 11/25/2016.
 */
public class Post {

    @Id
    private String postId;
    private String postName;
    private String description;
    private String userId;
    private Image image;
    private String requestTime;
    private String material;
    private String size;

    public Post() {
    }

    public Post(String postName, String description, String userId, String size, String requestTime, String material, Image image) {
        this.postName = postName;
        this.description = description;
        this.userId = userId;
        this.size = size;
        this.requestTime = requestTime;
        this.material = material;
        this.image = image;
    }

    public String getPostName() { return postName;}

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRequestTime() { return requestTime;}

    public void setRequestTime(String requestTime) { this.requestTime = requestTime;}

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {this.material = material;}

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {this.image= image;}

    @Override
    public String toString() {
        return "Post{" +
                "postName='" + postName + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                ", size='" + size + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", size='" + size + '\'' +
                ", material='" + material + '\'' +
                '}';
    }

}
