package com.vazheninapps.newssearcher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import com.vazheninapps.newssearcher.R
import com.vazheninapps.newssearcher.pojo.Article
import kotlinx.android.synthetic.main.item_article.view.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleAdapter @Inject constructor() : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    var onButtonClickListener: OnButtonClickListener? = null
    var onReachEndListener: OnReachEndListener? = null
    private var articleList: List<Article> = arrayListOf()

    interface OnButtonClickListener {
        fun onButtonClick(article: Article)
    }

    interface OnReachEndListener {
        fun onReachEnd()
    }

    fun getArticleList(): List<Article> {
        return articleList
    }

    fun addArticles(articles: List<Article>){
        (articleList as ArrayList<Article>).addAll(articles)
        notifyDataSetChanged()
    }

    fun clearArticles(){
        (articleList as ArrayList<Article>).clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {


        if (articleList.size >= 10 && onReachEndListener != null && position > articleList.size - 2) {
            onReachEndListener!!.onReachEnd()
        }

        val article = articleList[position]
        with(holder) {
            textViewTitle.text = article.title
            textViewDate.text = article.getDate()
            textViewAuthor.text = article.author
            Picasso.get().load(article.urlToImage).placeholder(R.drawable.placeholder).into(imageViewArticle)
            textViewDescription.text = article.description
            button.setOnClickListener { onButtonClickListener?.onButtonClick(article) }
        }}

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.textViewTitle
        val textViewDate: TextView = itemView.textViewDate
        val textViewAuthor: TextView = itemView.textViewAuthor
        val imageViewArticle: ShapeableImageView = itemView.imageViewArticle
        val textViewDescription: TextView = itemView.textViewDescription
        val button: Button = itemView.buttonShowInBrowser

    }
}



