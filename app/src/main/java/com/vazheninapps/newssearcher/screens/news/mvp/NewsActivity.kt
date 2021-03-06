package com.vazheninapps.newssearcher.screens.news.mvp

import android.animation.Animator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.VISIBLE
import android.view.View.GONE
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vazheninapps.newssearcher.R
import com.vazheninapps.newssearcher.adapters.ArticleAdapter
import com.vazheninapps.newssearcher.dagger.App
import com.vazheninapps.newssearcher.pojo.Article
import com.vazheninapps.newssearcher.screens.news.dagger.NewsActivityComponent
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.toolbar_search.*
import java.security.Key
import javax.inject.Inject

class NewsActivity : AppCompatActivity(), NewsContract.View {

    @Inject
    lateinit var adapter: ArticleAdapter
    @Inject
    lateinit var presenter: NewsContract.Presenter

    private var newsActivityComponent: NewsActivityComponent? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        newsActivityComponent =
            if (savedInstanceState != null && savedInstanceState.containsKey(Keys.KEY_COMPONENT)) {
                savedInstanceState.getSerializable(Keys.KEY_COMPONENT) as NewsActivityComponent
            } else {
                (application as App).appComponent.newsActivityComponent().create()
            }
        newsActivityComponent?.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        setUpRecyclerView()
        setUpListeners()
        presenter.attachView(this)
        presenter.readyToShowAnimation()
    }

    private fun setUpRecyclerView() {
        recyclerViewNews.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.onButtonClickListener = object : ArticleAdapter.OnButtonClickListener {
            override fun onButtonClick(article: Article) {
                presenter.buttonToBrowserClicked(article)
            }
        }

        adapter.onReachEndListener = object : ArticleAdapter.OnReachEndListener {
            override fun onReachEnd() {
                presenter.endReached(adapter.getArticleList().size / 10 + 1)
            }
        }

        recyclerViewNews.adapter = adapter
    }

    private fun setUpListeners() {
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when (s.toString()) {
                    "" -> buttonSearch.isEnabled = false
                    else -> buttonSearch.isEnabled = true
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        buttonSearch.setOnClickListener {
            presenter.buttonSearchClicked()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        if (isFinishing) {
            presenter.destroy()
            newsActivityComponent = null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(Keys.KEY_COMPONENT, newsActivityComponent)
    }


    override fun getQueryString(): String {
        return editTextSearch.text.toString().trim()
    }

    override fun clearArticles() {
        adapter.clearArticles()
    }


    override fun showArticles(articles: List<Article>) {
        adapter.addArticles(articles)
    }

    override fun showError(errorMessage: String?) {
        Snackbar.make(recyclerViewNews, errorMessage.toString(), Snackbar.LENGTH_LONG)
            .setDuration(4000).show()
    }


    override fun goToBrowser(uri: Uri) {
        startActivity(Intent(Intent.ACTION_VIEW, uri).addCategory(Intent.CATEGORY_BROWSABLE))
    }

    override fun showProgressBar() {
        progressBar.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = GONE
    }

    override fun showSplashAnimation() {
        val views = arrayListOf<View>(recyclerViewNews, include)

        val fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeInAnim.duration = 7000

        splash_animation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                splash_animation.visibility = GONE
                views.forEach {
                    it.startAnimation(fadeInAnim)
                    it.visibility = VISIBLE
                }
                presenter.viewIsReady()
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        })

        views.forEach { it.visibility = GONE }
        splash_animation.visibility = VISIBLE
        splash_animation.playAnimation()
    }

}
