package com.example.colosseum

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract val LoginActivity: Unit
    val mContext = this

    abstract fun setupEvents()
    abstract fun setValue()
    abstract fun setValues()


}