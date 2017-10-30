package com.go.archcompsproductdemo.db;

import com.go.archcompsproductdemo.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Generates dummy data and inserts them into the database */
class DatabaseInitUtil {

    private static final String[] NAME_SAMPLES = new String[]{
            "iPhone X", "Samsung Galaxy Note 8", "Bose QC35",
            "LG U6000 4K UHD TV", "Amazon Echo Touch", "Tesla Model 3"};
    private static final String TUMBNAIL_URL_SAMPLE = "http://placehold.it/150/771796";

    static void initializeDb(AppDatabase db) {
        List<Product> products = new ArrayList<>(NAME_SAMPLES.length);

        generateData(products);

        insertData(db, products);
    }

    private static void generateData(List<Product> products) {
        Random rnd = new Random();
        for (int i = 0; i < NAME_SAMPLES.length; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName(NAME_SAMPLES[i]);
            product.setThumbnailUrl(TUMBNAIL_URL_SAMPLE);
            products.add(product);
        }
    }

    private static void insertData(AppDatabase db, List<Product> products) {
        db.beginTransaction();
        try {
            db.productDao().insertAll(products);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
