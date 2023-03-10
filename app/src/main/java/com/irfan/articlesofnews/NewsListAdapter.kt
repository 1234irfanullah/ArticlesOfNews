package com.irfan.articlesofnews


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_clicked_news.view.*
import kotlinx.android.synthetic.main.item_news.*
import kotlinx.android.synthetic.main.item_news.view.*

@Suppress("UNREACHABLE_CODE")
class NewsListAdapter(private val context: Context, private val articles: List<Article>) :

    RecyclerView.Adapter<ViewHolder>(), SendLink, ClickedItem {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val newsItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(newsItemView)


    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = articles[position]

        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author

        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.image)


        holder.itemView.setOnClickListener {
            val url=currentItem.url
            OpenCustomTab(url)
        }
        holder.itemView.buttonOfShare.setOnClickListener {
            SendTheLinkOfUrl(currentItem.url)
        }
    }

    override fun SendTheLinkOfUrl(url: String?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, "Share this image with your friends $url")
        // type = "image/*"
        intent.type = "text/plain"
        val chooser = Intent.createChooser(intent, "Share this image using this link")
        context.startActivity(chooser)
    }


    override fun OpenCustomTab(url: String?) {
        //     val url = "https://google.com/"
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }


}


class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)

}

