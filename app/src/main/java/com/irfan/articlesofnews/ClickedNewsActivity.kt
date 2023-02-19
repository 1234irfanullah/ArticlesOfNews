package com.irfan.articlesofnews

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.BLACK
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_clicked_news.*
import kotlinx.android.synthetic.main.item_news.*

class ClickedNewsActivity : AppCompatActivity(),SendLink {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clicked_news)



      //  val content = intent.getStringExtra("content")
        val url = intent.getStringExtra("url")
        button.setOnClickListener {
            SendTheLinkOfUrl(url)

        }



       // val urlToImage = intent.getStringExtra("urlToImage")

        content_of_news.text = url
        content_of_news.setTextColor(Color.parseColor("#ffffff"))
        window.decorView.setBackgroundColor(BLACK);





        fun sendingTheImage() {
//            val intent = Intent(Intent.ACTION_SEND)
//
//
//            intent.putExtra(Intent.EXTRA_TEXT, "Share this image with your friends $url")
//            // type = "image/*"
//            intent.type = "text/plain"
//            val chooser = Intent.createChooser(intent, "Share this image using this link")
//            startActivity(chooser)
        }

    }

    override fun SendTheLinkOfUrl(url:String?) {
        val intent = Intent(Intent.ACTION_SEND)


        intent.putExtra(Intent.EXTRA_TEXT, "Share this image with your friends $url")
        // type = "image/*"
        intent.type = "text/plain"
        val chooser = Intent.createChooser(intent, "Share this image using this link")
        startActivity(chooser)
    }


}

