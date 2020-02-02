package com.andalus.abomed7at55.mn_edek_a7la.utils

import android.content.Context
import android.util.Log
import com.andalus.abomed7at55.mn_edek_a7la.data.AppDatabase
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

object FileManager {

    @Throws(IOException::class)
    fun copyDatabase(context: Context) {
        val outputFile = context.getDatabasePath(Constants.DATABASE_NAME)

        Log.d("NEW VERSION", AppDatabase.NEW_VERSION.toString() + "")
        Log.d("CURRENT VERSION", AppDatabase.currentVersion.toString() + "")

        if (AppDatabase.currentVersion < AppDatabase.NEW_VERSION) {
            val sharedPreferences = context.getSharedPreferences(Constants.METADATA_FILE_NAME, Context.MODE_PRIVATE).edit()
            sharedPreferences.putInt(Constants.VERSION_KEY, AppDatabase.NEW_VERSION)
            sharedPreferences.apply()

            val isDeleted = outputFile.delete()
            val isDataDeleted = context.deleteDatabase(Constants.DATABASE_NAME)

            Log.d("FILE DELETED", "$isDeleted")
            Log.d("DATABASE DELETED", "$isDataDeleted")

        }
        if (!outputFile.exists()) {
            val inputStream = context.assets.open(Constants.DATABASE_NAME)
            val file = context.filesDir
            val databaseFolder = File(file.parent, "databases")
            databaseFolder.mkdirs()
            val databaseFile = File(databaseFolder.absolutePath, Constants.DATABASE_NAME)
            val outputStream: OutputStream = FileOutputStream(databaseFile)
            val buffer = ByteArray(1024)
            var bufferSize: Int
            while (inputStream.read(buffer).also { bufferSize = it } > 0) {
                outputStream.write(buffer, 0, bufferSize)
            }
            inputStream.close()
            outputStream.flush()
            outputStream.close()
            Log.d("File", "DATABASE COPIED")
        }
    }

}