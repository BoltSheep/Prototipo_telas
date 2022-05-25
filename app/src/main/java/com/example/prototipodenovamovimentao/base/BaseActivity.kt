package com.example.prototipodenovamovimentao.base


import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.prototipodenovamovimentao.R
import com.example.prototipodenovamovimentao.databinding.ActivityBaseBinding


class BaseActivity : AppCompatActivity(){

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun move(view: View) {
        view.findNavController().navigate(R.id.action_movementFragment_to_fragment_filler)
    }


    override fun onBackPressed() {}

}