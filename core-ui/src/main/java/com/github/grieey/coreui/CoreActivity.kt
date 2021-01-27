package com.github.grieey.coreui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.grieey.coreui.notification.INotification
import com.github.grieey.coreui.notification.NotificationImpl

/**
 * description: 最顶层的activity
 * @date: 2021/1/22 16:04
 * @author: Grieey
 */
abstract class CoreActivity : AppCompatActivity(), INotification {
    /**
     * 方便获取顶层的对象
     */
    protected val parent: CoreActivity
        get() = this

    private val notificationDelegate: INotification by lazy { NotificationImpl(parent.window.decorView) }

    protected fun initNotficationStyle() {

    }

    override fun showNotificaion() {
        notificationDelegate.showNotificaion()
    }
}