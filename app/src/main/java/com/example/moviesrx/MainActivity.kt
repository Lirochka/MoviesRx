package com.example.moviesrx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesrx.adapters.MoviesAdapter
import com.example.moviesrx.data.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.moviesrx.network.MovieApiClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Добавляем recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val call = MovieApiClient.apiClient.getTopRatedMovies(API_KEY, "ru")

        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>, response: Response<MoviesResponse>
            ) {
                val movies = response.body()!!.results
                // Передаем результат в adapter и отображаем элементы
                recyclerView.adapter = MoviesAdapter(movies, R.layout.list_item_movie)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                // Логируем ошибку
                Log.e(TAG, t.toString())
            }
        })
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName

        private val API_KEY = "12842638c2c6e194ffe57856a319fe6d"
    }
}