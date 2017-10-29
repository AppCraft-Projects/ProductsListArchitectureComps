package com.go.archcompsproductdemo.service;

import com.go.archcompsproductdemo.model.Product;

import java.util.List;

public interface AsyncCallback {

    void onSuccess(final List<Product> productsListResults);

    void onFault(final String errorMsg);
}
