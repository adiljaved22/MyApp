package com.example.myapp

import android.app.Application

class RecipeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}