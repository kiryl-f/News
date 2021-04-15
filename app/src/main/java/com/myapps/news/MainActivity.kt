package com.myapps.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsRecView.addItemDecoration(DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL))

        val retrofit = Retrofit.Builder().baseUrl("https://content.guardianapis.com/").addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(NewsService::class.java)
        val call = service.getNews()
        call.enqueue(object : Callback<Base> {
            override fun onResponse(call: Call<Base>, response: retrofit2.Response<Base>) {
                newsRecView.adapter = NewsAdapter(response.body()?.response?.results as MutableList<Results>, baseContext)
            }

            override fun onFailure(call: Call<Base>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}