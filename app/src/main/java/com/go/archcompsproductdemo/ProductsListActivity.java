package com.go.archcompsproductdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.go.archcompsproductdemo.adapter.ProductsListAdapter;
import com.go.archcompsproductdemo.databinding.ActivityProductsListBinding;
import com.go.archcompsproductdemo.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProductsListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list);
        binding.setIsLoading(true);
        addDelay();

        ProductsListAdapter adapter = new ProductsListAdapter();
        adapter.setProductList(generateMockProductList());
        binding.productsList.setAdapter(adapter);
        binding.setIsLoading(false);
    }

    private List<Product> generateMockProductList() {
        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Product newProduct = new Product();
            newProduct.setId(i);
            newProduct.setName("Product " + i);
            newProduct.setThumbnailUrl("https://i.ebayimg.com/images/g/A8MAAOSwHaBWkDkk/s-l200.jpg");

            productList.add(newProduct);
        }

        return productList;
    }

    private void addDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
    }
}
