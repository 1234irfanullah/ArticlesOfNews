@file:Suppress("NAME_SHADOWING")

package com.irfan.articlesofnews

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_clicked_news.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_news.*
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: NewsListAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()


    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in", 2, "sports")
        news.enqueue(object : Callback<News1> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: retrofit2.Call<News1>, response: Response<News1>) {
                val news: News1? =response.body()

                if (news != null) {
                    Log.d("success", news.toString())
                    Log.d("infoOfresult", news.totalResults.toString())
                    mAdapter = NewsListAdapter(this@MainActivity, news.articles)
                    recyclerView.adapter = mAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    mAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: retrofit2.Call<News1>, t: Throwable) {
                Log.e("loss", "bad internet connection lets try after a moment", t)
            }
        })
    }






}