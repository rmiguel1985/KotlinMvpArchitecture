package com.adictosalainformatica.kotlinclean.utils

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout


object ProgressBarHelper {
    private var progressBar: ProgressBar? = null

    fun progressBarHandler(context: Context) {
        val layout = (context as Activity).findViewById<View>(android.R.id.content)
                .rootView as ViewGroup

        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
        progressBar?.isIndeterminate = true


        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT)

        val relativeLayout = RelativeLayout(context)

        relativeLayout.gravity = Gravity.CENTER
        relativeLayout.addView(progressBar)

        layout.addView(relativeLayout, params)

        hide()
    }

    fun show() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hide() {
        progressBar?.visibility = View.INVISIBLE
    }
}