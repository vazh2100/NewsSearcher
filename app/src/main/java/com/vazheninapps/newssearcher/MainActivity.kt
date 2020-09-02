package com.vazheninapps.newssearcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vazheninapps.newssearcher.api.ApiFactory
import com.vazheninapps.newssearcher.pojo.Response
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




       val disposable = ApiFactory
           .newsService
           .getNews()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({
               Log.d("11112222",  it.toString())

           },{
               Log.d("11112222", it.toString())
           }

           )

    }
}