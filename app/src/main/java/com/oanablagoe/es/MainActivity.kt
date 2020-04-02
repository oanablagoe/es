package com.oanablagoe.es

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oanablagoe.es.config.AppConfig


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppConfig.context = applicationContext
    }
}
