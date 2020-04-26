package com.andalus.abomed7at55.mn_edek_a7la.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.andalus.abomed7at55.mn_edek_a7la.services.SynchronizationService
import com.andalus.abomed7at55.mn_edek_a7la.utils.UtilFunctions.isNetworkAvailable

class NetworkStateReceiver : BroadcastReceiver() {

    var onNetworkStateReceive: (Boolean) -> Unit = {}

    override fun onReceive(context: Context?, intent: Intent?) {

        if (context?.applicationContext != null) {
            onNetworkStateReceive.invoke(isNetworkAvailable(context.applicationContext))
            if (isNetworkAvailable(context.applicationContext))
                context.startService(Intent(context, SynchronizationService::class.java))
        }

    }
}