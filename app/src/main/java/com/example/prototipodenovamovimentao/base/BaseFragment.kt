package com.example.prototipodenovamovimentao.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

open class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSyncViewCreated()
        addObservers(viewLifecycleOwner)
        setupView()
    }

    override fun onDestroyView() {
        onSyncDestroyView()
        super.onDestroyView()
    }

    open fun onSyncViewCreated() {}

    open fun onSyncDestroyView() {}

    open fun addObservers(owner: LifecycleOwner) {}

    open fun setupView() {}

}