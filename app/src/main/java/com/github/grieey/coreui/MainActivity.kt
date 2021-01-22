package com.github.grieey.coreui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : CoreActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}