package com.github.grieey.coreui.extension

import com.github.grieey.coreui.CoreActivity
import com.github.grieey.coreui.notification.NotificationImpl

/**
 * description: activity的扩展
 * @date: 2021/1/28 10:13
 * @author: Grieey
 */
fun CoreActivity.showNotification(config: NotificationImpl.() -> Unit) {
  (notificationDelegate as NotificationImpl).apply(config)
  showNotificaion()
}