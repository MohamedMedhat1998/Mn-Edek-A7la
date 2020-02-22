package com.andalus.abomed7at55.mn_edek_a7la.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.andalus.abomed7at55.mn_edek_a7la.services.SynchronizationService

class DatabaseReceiver(private val onReceive: () -> Unit) : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("db_receiver", "DATABASE COPIED")
        p0?.startService(Intent(p0, SynchronizationService::class.java))
        onReceive.invoke()
    }

}