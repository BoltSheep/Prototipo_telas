package com.example.prototipodenovamovimentao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prototipodenovamovimentao.base.BaseFragment

class FinishedTravelFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.viagem_concluida, container, false)
}