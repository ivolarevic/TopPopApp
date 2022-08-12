package com.example.toppopapp.utils

import android.view.View

interface InterfaceCard {
    fun onCardViewClick(view: View, position: Int, albumId: Long)
}