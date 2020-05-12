package com.example.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

abstract class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValue()

    }

    override fun setValue() {

    }

    override fun setupEvents() {

    }
}
