package com.go.archcompsproductdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.go.archcompsproductdemo.adapter.ProductsListAdapter;
import com.go.archcompsproductdemo.databinding.ActivityProductsListBinding;
import com.go.archcompsproductdemo.model.Product;
import com.go.archcompsproductdemo.viewmodel.ProductsListViewModel;

import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    private ActivityProductsListBinding mBinding;
    private ProductsListAdapter mAdapter;
    private ProductsListViewModel mViewModel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_products_list);

        mBinding.setIsLoading(true);

        mViewModel = ViewModelProviders.of(this).get(ProductsListViewModel.class);

        mAdapter = new ProductsListAdapter();
        mBinding.productsList.setAdapter(mAdapter);

        subscribeUi(mViewModel);
    }

    private void subscribeUi(ProductsListViewModel productsListViewModel) {
        productsListViewModel.getProducts().observe(this, new Observer<List<Product>>() {

            @Override public void onChanged(@Nullable List<Product> products) {
                if (products != null) {
                    mBinding.setIsLoading(false);
                    mAdapter.setProductList(products);
                } else {
                    mBinding.setIsLoading(true);
                }
            }
        });
    }
}
