package com.example.imran_mamirov_hw_5_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.imran_mamirov_hw_5_3.databinding.ActivityMainBinding
import com.example.imran_mamirov_hw_5_3.models.Character

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter = CartoonPagingAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchCharacters().observe(this) { data ->
            adapter.submitData(this.lifecycle, data)
        }
    }


    private fun fetchCharacters(): LiveData<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CartoonPagingSource()
            }
        ).liveData
    }
}