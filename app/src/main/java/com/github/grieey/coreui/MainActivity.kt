package com.github.grieey.coreui

import android.os.Bundle
import android.widget.TextView
import com.github.grieey.coreui.extension.showNotification

class MainActivity : CoreActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val tv = findViewById<TextView>(R.id.show_notification)
    tv.setOnClickListener {
      showNotification {
        content = "Hello!"
        disappearTimeout = 3000L
      }
    }
  }
}