package com.example.moviesrx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesrx.adapters.MoviesAdapter
import com.example.moviesrx.network.MovieApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Добавляем recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

       // Получаем Single
        val getTopRatedMovies = MovieApiClient.apiClient.getTopRatedMovies(API_KEY, "ru")

        getTopRatedMovies
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    val movies = it.results
                    // Передаем результат в adapter и отображаем элементы
                    recyclerView.adapter = MoviesAdapter(movies, R.layout.list_item_movie)
                },
                { error ->
                    Log.e(TAG, error.toString())
                }
            )
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName
        private val API_KEY = "12842638c2c6e194ffe57856a319fe6d"
    }
}