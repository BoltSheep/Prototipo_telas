package com.example.prototipodenovamovimentao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prototipodenovamovimentao.base.BaseFragment
import com.example.prototipodenovamovimentao.base.listeners.OnButtonClickListener

class MainActivity : BaseFragment(), OnButtonClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.preenchimento, container, false)
}



//fun onRadioButtonClicked(view: View) {
//    if (view is RadioButton) {
//        // Is the button now checked?
//        val checked = view.isChecked
//
//        // Check which radio button was clicked
//        when (view.getId()) {
//            R.id.radio_pirates ->
//                if (checked) {
//                    // Pirates are the best
//                }
//            R.id.radio_ninjas ->
//                if (checked) {
//                    // Ninjas rule
//                }
//        }
//    }
//}