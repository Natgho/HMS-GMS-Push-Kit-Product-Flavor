package com.demo.pushgh

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class GMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "PUSH_GMessagingService"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {

            Log.d(TAG, "Data payload: ${remoteMessage.data}")
        }

        if (remoteMessage.notification != null) {

            Log.d(TAG, "Notification body: ${remoteMessage.notification!!.body}")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d(TAG, "New token received: $token")
    }

}