package com.jakeesveld.coroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.annotation.WorkerThread
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
object NetworkAdapter {
    interface NetworkCallback {
        fun returnResult(success: Boolean?, result: String)
        fun returnImageResult(success: Boolean?, result: Bitmap?, requestUrl: String)
    }

    @WorkerThread
    fun httpGetRequest(urlString: String, callback: NetworkCallback) {
        var result = ""
        var success = false
        var connection: HttpURLConnection? = null
        var stream: InputStream? = null
        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                stream = connection.inputStream
                if (stream != null) {
                    val reader = BufferedReader(InputStreamReader(stream))
                    val builder = StringBuilder()
                    var line: String? = reader.readLine()
                    while (line != null) {
                        builder.append(line)
                        line = reader.readLine()
                    }
                    result = builder.toString()
                    success = true
                }
            } else {
                result = responseCode.toString()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()

            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            callback.returnResult(success, result)
        }
        //                return result;
    }

    @WorkerThread
    fun getBitmapFromURL(urlString: String, callback: NetworkCallback) {
        var result: Bitmap? = null
        var connection: HttpURLConnection? = null
        try {

            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream

            result = BitmapFactory.decodeStream(input)
//            Log.i("Bitmaps", String.format("thumb %d", result.byteCount))


        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()
        }
        callback.returnImageResult(true, result, urlString)
    }
}