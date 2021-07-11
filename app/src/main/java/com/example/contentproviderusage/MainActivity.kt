package com.example.contentproviderusage

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //insert

        var values = ContentValues()
        values.put("id", 90123)
        values.put("title", "Phone Android 1")
        values.put("price", 909090)

        var newUri = contentResolver.insert(
            Uri.withAppendedPath(
                Uri.parse("content://in.bitcode.products"),
                "products"
            ),
            values
        )

        Log.e("tag", newUri.toString())


        //var contentResolver = contentResolver
        //var contentUri = Uri.parse("content://in.bitcode.products")
        //contentUri = Uri.withAppendedPath(contentUri, "products")
        //contentUri = Uri.withAppendedPath(contentUri, "190")

        if(newUri != null) {

            var cursor = contentResolver.query(
                newUri,
                //contentUri,
                null,
                null,
                null,
                null
            )
            /*
        var cursor = contentResolver.query(
            contentUri,
            null,
            "id = ?",
            arrayOf("190"),
            null
        )
         */

            while (cursor?.moveToNext() == true) {
                Log.e(
                    "tag",
                    "id : ${cursor.getInt(0)} title: ${cursor.getString(1)} price: ${cursor.getInt(2)}"
                )
            }

            var newValues = ContentValues()
            newValues.put("title", "1 Android Phone New Updated")
            newValues.put("price", 10000)

            var count = contentResolver.update(newUri, newValues, null, null)
            Log.e("tag", "$count records updated")

            count = contentResolver.delete(newUri, null, null)
            Log.e("tag", "$count records deleted")


        }

    }
}