package com.github.grieey.coreui.notification

import android.view.View
import android.view.ViewGroup

/**
 * description: 页面通知的实现类
 * @date: 2021/1/27 16:11
 * @author: Grieey
 */
class NotificationImpl(private val decorView: View) : INotification {

    var notificationView: INotification = DefaultNotificationView(decorView.context)
        set(value) {
            if (value is View) {
                field = value
                (decorView as ViewGroup).addView(field as View)
            }
        }

    init {
        (decorView as ViewGroup).addView(notificationView as View)
    }

    override fun showNotificaion() {
        notificationView.showNotificaion()
    }
}