package com.example.prototipodenovamovimentao.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prototipodenovamovimentao.R


class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    override fun onBackPressed() {}

}