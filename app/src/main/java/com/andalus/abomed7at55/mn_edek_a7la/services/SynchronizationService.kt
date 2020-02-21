package com.andalus.abomed7at55.mn_edek_a7la.services

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.andalus.abomed7at55.mn_edek_a7la.sync.Synchronizer
import org.koin.android.ext.android.inject

class SynchronizationService : LifecycleService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val synchronizer: Synchronizer by inject()
        synchronizer.sync()
        return super.onStartCommand(intent, flags, startId)
    }

}