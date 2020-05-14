package com.example.colosseum

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract val REQ_FOR_PICK_IMAGE_FROM_GALLERY: Int
    val mContext=this

    abstract fun setupEvents()
    abstract fun setValues()


}