package io.mediaportal.ironsource

import android.app.NotificationManager
import android.content.Context
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.core.app.NotificationCompat
import io.mediaportal.ironsource.base.BaseActivity
import io.mediaportal.ironsource.model.ButtonToAction
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainPresenter>(), MainView {


    override fun getLayout() = R.layout.activity_main

    override fun initInjector() {
        IronApplication.instance.component.inject(this)
    }

    override fun initViews() {
        main_btn.setOnClickListener { onUserClickBtn(it.tag as ButtonToAction) }
    }

    override fun setState(state: State) {
        when (state) {
            State.LOADING -> {
                main_btn.visibility = GONE
                main_txt.visibility = GONE
                main_progressbar.visibility = VISIBLE
            }
            State.SUCCESS -> {
                main_btn.visibility = VISIBLE
                main_txt.visibility = GONE
                main_progressbar.visibility = GONE
            }
            State.ERROR -> {
                main_btn.visibility = GONE
                main_txt.visibility = VISIBLE
                main_progressbar.visibility = GONE
            }
        }
    }

    override fun setActionToButton(type: ButtonToAction) {
        main_btn.tag = type
    }

    private fun onUserClickBtn(type: ButtonToAction) {
        when (type.type) {
            "animation" -> animationAction()
            "toast" -> toastAction()
            "call" -> callAction()
            "notification" -> notificationAction()
        }
    }

    private fun animationAction() {
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        main_btn.startAnimation(rotate)
    }

    private fun toastAction() {
        Toast.makeText(baseContext, R.string.toast_txt, Toast.LENGTH_LONG).show()
    }

    private fun callAction() {
        //TODO call intent
    }

    private fun notificationAction() {
        val mBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, mBuilder.build())
    }
}