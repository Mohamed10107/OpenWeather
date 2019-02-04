package com.coderbot.openweather.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.coderbot.openweather.view.loading.LoadingDialog
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var loading: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        loading = LoadingDialog(this@BaseActivity)
    }

    abstract fun initViews()

    abstract fun initLiveDataListeners()

    protected fun showLoading() {
        loading.show()
    }

    protected fun dismissLoading() {
        loading.dismiss()
    }

}