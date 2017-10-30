package com.go.archcompsproductdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer mId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("thumbnailUrl")
    @Expose
    private String mThumbnailUrl;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.mThumbnailUrl = thumbnailUrl;
    }

    public Product() { super(); }

    public Product(Product newProduct) {
        super();
        this.setId(newProduct.getId());
        this.setName(newProduct.getName());
        this.setThumbnailUrl(newProduct.getThumbnailUrl());
    }
}
