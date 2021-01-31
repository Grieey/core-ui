package com.github.grieey.coreui

import android.os.Bundle
import android.widget.TextView
import com.github.grieey.core_ext.click
import com.github.grieey.coreui.databinding.ActivityMainBinding
import com.github.grieey.coreui.extension.showNotification

class MainActivity : CoreActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.showNotification.click {
      showNotification {
        content = "Hello!"
        disappearTimeout = 3000L
      }
    }
  }
}