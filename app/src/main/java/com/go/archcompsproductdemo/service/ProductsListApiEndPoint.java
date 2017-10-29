package com.go.archcompsproductdemo.service;

import com.go.archcompsproductdemo.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsListApiEndPoint<ResultType> {

    @GET("/products")
    Call<List<Product>> getProducts();
}
