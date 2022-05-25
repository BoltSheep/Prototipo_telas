package com.example.prototipodenovamovimentao.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prototipodenovamovimentao.databinding.ActivityBaseBinding


class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onBackPressed() {}
}