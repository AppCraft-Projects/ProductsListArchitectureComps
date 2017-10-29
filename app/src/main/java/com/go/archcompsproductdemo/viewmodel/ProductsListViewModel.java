package com.go.archcompsproductdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.go.archcompsproductdemo.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Product>> mProducts;

    public ProductsListViewModel(@NonNull Application application) {
        super(application);
        mProducts = new MutableLiveData<>();
    }

    public LiveData<List<Product>> getProducts() {
        if(mProducts.getValue() == null) {
            loadProducts();
        }
        return mProducts;
    }

    private void loadProducts() {
        new MockProductsListGeneratorTask().execute(10);
    }

    private class MockProductsListGeneratorTask extends AsyncTask<Integer, Void, List<Product>> {

        @Override protected List<Product> doInBackground(Integer... counters) {
            doDelay();
            return generateMockProductList(counters[0]);
        }

        @Override protected void onPostExecute(List<Product> results) {
            super.onPostExecute(results);

            if(results != null) {
                mProducts.setValue(results);
            } else {
                mProducts.setValue(new ArrayList<Product>());
            }
        }

        private List<Product> generateMockProductList(final int maxCount) {
            List<Product> productList = new ArrayList<>();

            for (int i = 0; i < maxCount; i++) {
                Product newProduct = new Product();
                newProduct.setId(i);
                newProduct.setName("Product " + i);
                newProduct.setThumbnailUrl("https://i.ebayimg.com/images/g/A8MAAOSwHaBWkDkk/s-l200.jpg");

                productList.add(newProduct);
            }

            return productList;
        }

        private void doDelay() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}
        }
    }
}
