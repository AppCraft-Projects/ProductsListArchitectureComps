package com.go.archcompsproductdemo.service;

import android.support.annotation.NonNull;
import android.support.coreutils.BuildConfig;

import com.go.archcompsproductdemo.model.Product;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private AsyncCallback asyncCallback;
    private Retrofit retrofit;

    public NetworkService() {
        OkHttpClient.Builder httpClient = buildOkHttpClient();
        retrofit = buildRetrofit(httpClient);
    }

    @NonNull private Retrofit buildRetrofit(OkHttpClient.Builder httpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://demo6667493.mockable.io/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull private OkHttpClient.Builder buildOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor).build();
        }
        httpClient.build();
        return httpClient;
    }

    public void registerCallback(AsyncCallback asyncCallback){
        this.asyncCallback = asyncCallback;
    }

    public void getProducts() {
        ProductsListApiEndPoint productsListApiEndPoint = retrofit.create(ProductsListApiEndPoint.class);

        Call products = productsListApiEndPoint.getProducts();
        products.enqueue(new Callback<List<Product>>() {

            @Override public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if(response.isSuccessful()){
                    asyncCallback.onSuccess(response.body());
                }
            }

            @Override public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                t.printStackTrace();
                asyncCallback.onFault(null);
            }
        });
    }
}
