package com.demo.pushgh

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.api.HuaweiApiAvailability
import com.huawei.hms.push.HmsMessaging


class PushManager(private val context: Context) {

    companion object {
        private const val TAG = "PUSH_PushManager"
    }

    private val service: MobileService = checkServiceAvailability()

    fun subscribe(topic: String) {

        when (service) {
            MobileService.GOOGLE -> subscribeToFirebaseTopic(topic)
            MobileService.HUAWEI -> subscribeToHuaweiTopic(context, topic)
            MobileService.OTHER -> Log.d(TAG, "Other service.")
        }
    }

    private fun checkServiceAvailability(): MobileService {

        return if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
            == ConnectionResult.SUCCESS) {

            Log.d(TAG, "Google Play Services available.")
            MobileService.GOOGLE
        } else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context)
            == com.huawei.hms.api.ConnectionResult.SUCCESS) {

            Log.d(TAG, "Huawei Mobile Services available.")
            MobileService.HUAWEI
        } else {

            Log.d(TAG, "Neither Google nor Huawei services are available.")
            MobileService.OTHER
        }
    }

    private fun subscribeToFirebaseTopic(topic: String) {

        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnSuccessListener {
                Log.d(TAG, "Subscribed to Firebase topic: $topic")
            }
            .addOnFailureListener {
                Log.w(TAG, "Failed to subscribe to Firebase topic: $topic", it)
            }
    }

    private fun subscribeToHuaweiTopic(context: Context, topic: String) {

        HmsMessaging.getInstance(context).subscribe(topic)
            .addOnSuccessListener {
                Log.d(TAG, "Subscribed to Huawei topic: $topic")
            }
            .addOnFailureListener {
                Log.w(TAG, "Failed to subscribe to Huawei topic: $topic", it)
            }
    }
}