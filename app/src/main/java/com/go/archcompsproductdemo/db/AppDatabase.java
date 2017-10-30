package com.go.archcompsproductdemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.go.archcompsproductdemo.db.dao.ProductDao;
import com.go.archcompsproductdemo.model.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    static final String DATABASE_NAME = "products-demo-db";

    public abstract ProductDao productDao();
}
