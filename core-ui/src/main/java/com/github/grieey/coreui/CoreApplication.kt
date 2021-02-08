package com.github.grieey.coreui

import android.app.Application

/**
 * description: 最顶层的application
 * @date: 2021/1/22 16:05
 * @author: Grieey
 */
abstract class CoreApplication : Application() {

  override fun onCreate() {
    INSTANCE = this
    super.onCreate()
  }

  companion object {
    lateinit var INSTANCE: CoreApplication
      private set
  }
}