package com.go.archcompsproductdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.go.archcompsproductdemo.model.Product;
import com.go.archcompsproductdemo.service.AsyncCallback;
import com.go.archcompsproductdemo.service.NetworkService;

import java.util.ArrayList;
import java.util.List;

public class ProductsListViewModel extends AndroidViewModel implements AsyncCallback {

    private MutableLiveData<List<Product>> mProducts;
    private NetworkService mNetworkService;

    public ProductsListViewModel(@NonNull Application application) {
        super(application);
        mProducts = new MutableLiveData<>();

        mNetworkService = new NetworkService();
        mNetworkService.registerCallback(this);
    }

    public LiveData<List<Product>> getProducts() {
        if(mProducts.getValue() == null) {
            loadProducts();
        }
        return mProducts;
    }

    private void loadProducts() {
        mNetworkService.getProducts();
    }

    @Override public void onSuccess(List<Product> productsListResults) {
        if(productsListResults != null) {
            mProducts.setValue(productsListResults);
        } else {
            mProducts.setValue(new ArrayList<Product>());
        }
    }

    @Override public void onFault(String errorMsg) {
        // TODO
    }
}
