@file:Suppress("NAME_SHADOWING")

package com.irfan.articlesofnews

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_clicked_news.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_news.*
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(),SendLink{

    private lateinit var mAdapter: NewsListAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()


    }
    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in",2,"health")
        news.enqueue(object : Callback<News1> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: retrofit2.Call<News1>, response: Response<News1>) {
                val news: News1? =
                    response.body()

                if (news != null) {
                    Log.d("success", news.toString())
                    Log.d("infoOfresult", news.totalResults.toString())
                    mAdapter = NewsListAdapter(this@MainActivity,news.articles,)
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

    override fun SendTheLinkOfUrl(url: String?) {
        val intent = Intent(Intent.ACTION_SEND)


        intent.putExtra(Intent.EXTRA_TEXT, "Share this image with your friends $url")
        // type = "image/*"
        intent.type = "text/plain"
        val chooser = Intent.createChooser(intent, "Share this image using this link")
        startActivity(chooser)
    }

}
