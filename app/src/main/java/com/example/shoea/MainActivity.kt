package com.example.shoea

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.Request
import com.example.shoea.Adpters.NameListAdapter
import com.example.shoea.Adpters.ProductAdapter
import com.example.shoea.Interface.ApiInterface
import com.example.shoea.Models.ProductData
import com.example.shoea.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class MainActivity : AppCompatActivity() {

    lateinit var myAdpter: ProductAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityMainBinding
    private  val BASE_URL = "https://dummyjson.com/"


    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        checkInternetConnection()
    }



    private fun checkInternetConnection() {
        val conMgr = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (conMgr.getActiveNetworkInfo() != null
            && conMgr.getActiveNetworkInfo()!!.isAvailable()
            && conMgr.getActiveNetworkInfo()!!.isConnected()) {
            getMyData()
        } else {
            // Internet is not connected, show dialog
            showDialog()
        }
    }

    private  fun getMyData() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
//            .create(ApiInterface::class.java)
        val productApi = retrofitBuilder.create(ApiInterface::class.java)
        val retrofitData = productApi.getData()

        retrofitData.enqueue(object : Callback<List<ProductData>?> {
            override fun onResponse(
                call: Call<List<ProductData>?>,
                response: Response<List<ProductData>?>
            ) {
                if (response.isSuccessful) {
                    val productList = response.body()!!
                    binding.recyclerView.setHasFixedSize(true)
                    linearLayoutManager= LinearLayoutManager(this@MainActivity)
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    myAdpter = ProductAdapter(this@MainActivity,productList)
                    binding.recyclerView.adapter=myAdpter
                    myAdpter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this@MainActivity,"NOT DONE",Toast.LENGTH_SHORT).show()
                }




            }

            override fun onFailure(call: Call<List<ProductData>?>, t: Throwable) {
                val dialog = AlertDialog.Builder(this@MainActivity)
                    .setTitle("Feaching Problem")
                    .setMessage("Data Not Fetch Properly")
                    .setPositiveButton("OK") { _, _ -> }
                    .create()
                dialog.show()

            }
        })

    }
    private fun showDialog() {
        val dialog = AlertDialog.Builder(this@MainActivity)
            .setTitle("Internet Connection Error")
            .setMessage("Turn on your internet connection to continue.")
            .setPositiveButton("OK") { _, _ -> }
            .create()
        dialog.show()
    }
    }


