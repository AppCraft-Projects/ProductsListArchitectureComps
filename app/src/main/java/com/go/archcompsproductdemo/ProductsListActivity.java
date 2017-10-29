package com.go.archcompsproductdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.go.archcompsproductdemo.databinding.ActivityProductsListBinding;

public class ProductsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProductsListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list);
        binding.setIsLoading(true);
    }
}
