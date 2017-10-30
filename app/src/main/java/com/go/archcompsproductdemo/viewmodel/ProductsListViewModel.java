package com.go.archcompsproductdemo.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.go.archcompsproductdemo.db.DatabaseCreator;
import com.go.archcompsproductdemo.model.Product;

import java.util.List;

public class ProductsListViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private LiveData<List<Product>> mProducts;

    public ProductsListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Product>> getProducts() {
        if(mProducts == null || mProducts.getValue() == null) {
            loadProducts();
        }
        return mProducts;
    }

    private void loadProducts() {
        final DatabaseCreator databaseCreator = DatabaseCreator.getInstance(this.getApplication());

        LiveData<Boolean> databaseCreated = databaseCreator.isDatabaseCreated();
        mProducts = Transformations.switchMap(databaseCreated,
                new Function<Boolean, LiveData<List<Product>>>() {

                @Override public LiveData<List<Product>> apply(Boolean isDbCreated) {
                    if (!Boolean.TRUE.equals(isDbCreated)) { // Not needed here, but watch out for null
                        //noinspection unchecked
                        return ABSENT;
                    } else {
                        //noinspection ConstantConditions
                        return databaseCreator.getDatabase().productDao().loadAllProducts();
                    }
                }
            });

        databaseCreator.createDb(this.getApplication());
    }
}
