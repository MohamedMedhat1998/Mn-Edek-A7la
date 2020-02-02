package com.andalus.abomed7at55.mn_edek_a7la.services

import android.app.IntentService
import android.content.Intent
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import com.andalus.abomed7at55.mn_edek_a7la.utils.FileManager


class FileManagerService : IntentService(Constants.FILE_SERVICE) {

    override fun onHandleIntent(p0: Intent?) {
        FileManager.copyDatabase(this)
    }

}