package com.github.grieey.coreui.notification

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * description: 默认通知样式
 * @date: 2021/1/27 16:08
 * @author: Grieey
 */
class DefaultNotificationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), INotification {

    var animator : Animator = ObjectAnimator.ofFloat()

    override fun showNotificaion() {
        animator.start()
    }
}