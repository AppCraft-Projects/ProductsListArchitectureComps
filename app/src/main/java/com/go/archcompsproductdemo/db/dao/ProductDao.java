package com.go.archcompsproductdemo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.go.archcompsproductdemo.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    LiveData<List<Product>> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Product> products);
}
