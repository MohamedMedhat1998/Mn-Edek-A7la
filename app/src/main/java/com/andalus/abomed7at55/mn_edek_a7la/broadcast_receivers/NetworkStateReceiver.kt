package com.andalus.abomed7at55.mn_edek_a7la.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import com.andalus.abomed7at55.mn_edek_a7la.services.SynchronizationService


class NetworkStateReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        //Toast.makeText(p0, "Changed ${p1?.action}", Toast.LENGTH_SHORT).show()

        Log.d("Changed", "${p1?.action}")

        val wifiManager = p0?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        val info = wifiManager!!.connectionInfo

        /*Log.d("info","${info.networkId}")
        Log.d("bssid","${info.bssid}")
        Log.d("ipAddress","${info.ipAddress}")*/

        if (info.ipAddress != 0) {
            //Toast.makeText(p0, "Connected", Toast.LENGTH_SHORT).show()
            p0?.startService(Intent(p0, SynchronizationService::class.java))
        }

    }

}