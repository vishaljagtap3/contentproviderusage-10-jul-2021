package com.example.contentproviderusage

import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class NewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //getSettings()
        //readCallLog();
        readSMSes();
    }

    private fun readSMSes() {
        var smsUri = Uri.parse("content://sms")
        //var smsUri = Uri.parse("content://sms/inbox")

        var cursor = contentResolver.query(
            smsUri,
            null,
            null,
            null,
            null
        )

        for(col in cursor?.columnNames!!) {
            mt("col is $col")
        }

    }

    private fun readCallLog() {
        var cursor = contentResolver.query(
            android.provider.CallLog.Calls.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        for(col in cursor?.columnNames!!) {
            mt("col is $col")
        }

        var colIndexName = cursor.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME)
        var colIndexNumber = cursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER)
        var colIndexCallType = cursor.getColumnIndex(android.provider.CallLog.Calls.TYPE)
        var colIndexDateTime = cursor.getColumnIndex(android.provider.CallLog.Calls.DATE)

        while(cursor.moveToNext()) {
            mt("${cursor.getString(colIndexName)} -- ${cursor.getString(colIndexNumber)} -- ${cursor.getInt(colIndexCallType)} -- ${cursor.getString(colIndexDateTime)}")
        }

        cursor.close()

    }

    private fun getSettings() {

        var cursor = contentResolver.query(
            android.provider.Settings.System.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        for(col in cursor?.columnNames!!) {
            mt("col is $col")
        }

        var colId = cursor.getColumnIndex(android.provider.Settings.System._ID)
        var colName = cursor.getColumnIndex(android.provider.Settings.System.NAME)
        var colValue = cursor.getColumnIndex(android.provider.Settings.System.VALUE)

        while (cursor.moveToNext()) {
            mt("${cursor.getLong(colId)}  ${cursor.getString(colName)}  --> ${cursor.getString(colValue)}")
        }

    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }
}