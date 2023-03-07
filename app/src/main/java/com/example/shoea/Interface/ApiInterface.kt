package com.example.shoea.Interface

import com.example.shoea.Models.ProductData
import retrofit2.http.GET
import retrofit2.Call

interface ApiInterface {
    @GET("products")
     fun getData(): Call<List<ProductData>>

}