package com.demo.pushgh

import android.util.Log
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage

class HMessagingService : HmsMessageService() {

    companion object {
        private const val TAG = "PUSH_HMessagingService"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage?.from}")

        if (remoteMessage?.data != null) {

            Log.d(TAG, "Data payload: ${remoteMessage.data}")
        }

        if (remoteMessage?.notification != null) {

            Log.d(TAG, "Notification body: ${remoteMessage.notification!!.body}")
        }
    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)

        Log.d(TAG, "New token received: $token")
    }
}