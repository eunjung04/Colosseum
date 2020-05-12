package com.example.colosseum

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract val companion: Unit
    val mContext = this

    abstract fun setupEvents()
    abstract fun setValue()







}