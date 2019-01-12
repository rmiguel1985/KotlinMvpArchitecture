package com.adictosalainformatica.kotlinclean.base.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adictosalainformatica.kotlinclean.utils.ProgressBarHelper
import com.tapadoo.alerter.Alerter

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ProgressBarHelper.progressBarHandler(this)
    }

    fun showProgress() = ProgressBarHelper.show()

    fun hideProgress() = ProgressBarHelper.hide()

    fun showError(message: String = "Unexpected error") = showAlert(message, "Error", Color.RED)

    fun showError(resId: Int) = showError(getString(resId))

    fun showWarning(message: String) = showAlert(message, "Warning", Color.YELLOW)

    fun showWarning(resId: Int) = showWarning(getString(resId))

    fun showMessage(message: String) = showAlert(message, "Info", Color.GREEN)

    fun showMessage(stringId: Int) = showMessage(getString(stringId))

    private fun showAlert(message: String, title: String, color: Int) {
        Alerter.create(this)
                .setTitle(title)
                .setText(message)
                .hideIcon()
                .setDuration(2000)
                .setBackgroundColorInt(color)
                .show()
    }
}