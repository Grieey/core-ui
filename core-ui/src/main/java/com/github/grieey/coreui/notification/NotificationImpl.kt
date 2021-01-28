package com.github.grieey.coreui.notification

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.Gravity
import android.view.View
import android.view.View.NO_ID
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.doOnLayout
import androidx.core.view.postDelayed
import androidx.core.view.setPadding
import com.github.grieey.core_ui.R
import java.util.*
import kotlin.concurrent.schedule

/**
 * description: 页面通知的实现类
 * @date: 2021/1/27 16:11
 * @author: Grieey
 */
class NotificationImpl(private val decorView: View) : INotification {

  private var addedView = false
    get() {
      if (!field) {
        field = isViewAdded()
      }

      return field
    }

  @Volatile
  private var isAnimating = false

  /**
   * 提示的文本view
   */
  var notificationView = AppCompatTextView(decorView.context)
    .apply {
      textSize = 60F
      setTag(R.id.notification_view, true)
      setPadding(48)
      setTextColor(resources.getColor(R.color.white_95, null))
      setBackgroundResource(R.drawable.shape_purple200_20)
    }
    set(value) {
      field = value
      (decorView as ViewGroup).addView(field)
    }

  /**
   * 提示的内容
   */
  var content: CharSequence = ""
    set(value) {
      field = value
      notificationView.text = field
    }

  /**
   * 文本背景
   */
  var backgroundResId = NO_ID
    set(value) {
      if (value != NO_ID) {
        field = value
        notificationView.setBackgroundResource(field)
      }
    }

  /**
   * 文本颜色
   */
  var textColor: Int = NO_ID
    set(value) {
      if (value != NO_ID) {
        field = value
        notificationView.setTextColor(field)
      }
    }

  /**
   * 文本大小
   */
  var textSize: Float = 12F
    set(value) {
      if (value != 0F) {
        field = value
        notificationView.textSize = field
      }
    }

  var duration: Long = 330L

  /**
   * 显示时间
   */
  var disappearTimeout: Long = 330L

  /**
   * 自定义动画
   */
  var animations: ((view: AppCompatTextView) -> AnimatorSet)? = null

  init {

  }

  override fun showNotificaion() {
    if (isAnimating) return
    if (animations != null) {
      val set = animations
        ?.invoke(notificationView)
        ?.also { set ->
          set.start()
        }

      notificationView.postDelayed(disappearTimeout) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
          set?.reverse()
        } else {
          // 自动消失动画
        }
      }
    } else {
      val perfomAnimation = { detal: Float ->
        val animator = ObjectAnimator.ofFloat(notificationView, "translationY", -detal, detal * 2F).apply {
          duration = this@NotificationImpl.duration
        }
        isAnimating = true
        Timer().schedule(disappearTimeout + duration * 2) {
          isAnimating = false
        }
        animator.start()
        notificationView.postDelayed(disappearTimeout + duration) {
          animator.reverse()
        }
      }
      if (!addedView) {
        addView()
        notificationView.doOnLayout {
          perfomAnimation(it.height.toFloat())
        }
      } else {
        perfomAnimation(notificationView.height.toFloat())
      }
    }
  }

  private fun isViewAdded(): Boolean {
    for (i in 0 until (decorView as ViewGroup).childCount) {
      if ((decorView.getChildAt(i).getTag(R.id.notification_view) as? Boolean) == true) return true
    }

    return false
  }

  private fun addView() {
    (decorView as ViewGroup)
      .addView(notificationView,
        FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
          .apply {
            this.gravity = Gravity.CENTER_HORIZONTAL
          })
  }
}