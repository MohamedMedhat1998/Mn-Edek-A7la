package com.andalus.abomed7at55.mn_edek_a7la.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.andalus.abomed7at55.mn_edek_a7la.services.SynchronizationService

class DatabaseReceiver(private val onReceive: () -> Unit) : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0, "DATABASE COPIED", Toast.LENGTH_SHORT).show()
        p0?.startService(Intent(p0, SynchronizationService::class.java))
        onReceive.invoke()
    }

}