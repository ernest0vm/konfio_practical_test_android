package com.example.dogswelove.ui.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogswelove.R
import com.example.dogswelove.ui.viewmodel.DogViewModel
import com.example.dogswelove.utils.SpacingItemDecoration

class MainActivity : AppCompatActivity() {
    private val viewModel: DogViewModel by viewModels()
    private lateinit var adapter: DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NormalTheme)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DogAdapter()
        val spacing = resources.getDimensionPixelSize(R.dimen.spacing_20dp)
        recyclerView.addItemDecoration(
            SpacingItemDecoration(spacing)
        )
        recyclerView.adapter = adapter

        viewModel.getDogs().observe(this) { dogs ->
            adapter.setDogs(dogs)
        }
    }
}