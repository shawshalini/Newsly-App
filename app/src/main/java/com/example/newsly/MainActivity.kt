package com.example.newsly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    //private var articles= mutableListOf<Article>()
    val TAG="MainActivity"
    var pageNum=1
    var totalResults = -1
   // var newsList=findViewById<RecyclerView>(R.id.newsList)
   // private var mInterstitialAd: InterstitialAd? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //admobcode
//        MobileAds.initialize(this) {}
//        var adRequest = AdRequest.Builder().build()
//
//        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
//            override fun onAdFailedToLoad(adError: LoadAdError) {
//                mInterstitialAd = null
//            }
//
//            override fun onAdLoaded(interstitialAd: InterstitialAd) {
//
//                mInterstitialAd = interstitialAd
//            }
//        })
//        if (mInterstitialAd != null) {
//            mInterstitialAd?.show(this)
//        }
//        adapter=NewsAdapter(this@MainActivity,articles)
//        newsList.adapter = adapter
//        newsList.layoutManager=LinearLayoutManager(this@MainActivity)
        //Log.d(TAG,"Total count -${newsList.layoutManager.fin}")

        getNews()
    }
    private fun getNews(){
        val news=NewsService.newsInterface.getHeadlines("in",pageNum)
        val newsList=findViewById<RecyclerView>(R.id.newsList)
        news.enqueue(object: Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news=response.body()
                if(news!=null){
                   totalResults=news.totalResults
                    Log.d("codingdecoding",news.toString())
                    adapter=NewsAdapter(this@MainActivity,news.articles)
                    newsList.adapter = adapter
                    newsList.layoutManager=LinearLayoutManager(this@MainActivity)

                    //articles.addAll(news.articles)
                    //adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
              Log.d("codingdecoding","Error in fetching news",t)
            }


        }
        )
    }
}