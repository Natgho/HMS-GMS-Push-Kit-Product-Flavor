package com.demo.pushgh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PUSH_MainActivity"
    }

    private lateinit var mPushManager: PushManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPushManager = PushManager(applicationContext)

        buttonSubscribe.setOnClickListener {
            mPushManager.subscribe("sports")
        }
    }

}