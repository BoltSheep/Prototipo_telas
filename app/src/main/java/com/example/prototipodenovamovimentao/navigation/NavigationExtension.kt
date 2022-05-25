package com.example.prototipodenovamovimentao.navigation

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController

fun View.navigate(directions: NavDirections, navOptions: NavOptions? = null) = try {
    try {
        findNavController().navigate(directions, navOptions)
    } catch (e: IllegalStateException) {
        e.printStackTrace()
    }
} catch (e: IllegalArgumentException) {
    e.printStackTrace()
}