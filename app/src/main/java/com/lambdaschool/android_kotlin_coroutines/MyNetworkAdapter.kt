package com.lambdaschool.android_kotlin_coroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.io.IOException
import kotlinx.io.InputStream
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object MyNetworkAdapter {
    internal val REQUEST_GET = "GET"
    internal val REQUEST_POST = "POST"
    internal val REQUEST_PUT = "PUT"
    internal val REQUEST_DELETE = "DELETE"
    private val READ_TIMEOUT = 3000
    private val CONNECT_TIMEOUT = 3000

    @JvmOverloads
    fun httpRequest(
        url: String,
        requestMethod: String = REQUEST_GET,
        requestBody: JSONObject? = null,
        headerProperties: Map<String, String>? = null
    ): String {
        var httpResult = ""
        var inputStream: InputStream? = null
        var httpURLConnection: HttpURLConnection? = null

        try {
            val urlObject = URL(url)
            httpURLConnection = urlObject.openConnection() as HttpURLConnection
            httpURLConnection.readTimeout = READ_TIMEOUT
            httpURLConnection.connectTimeout = CONNECT_TIMEOUT
            httpURLConnection.requestMethod = requestMethod

            if (headerProperties != null) {
                for ((key, value) in headerProperties) {
                    httpURLConnection.setRequestProperty(key, value)
                }
            }

            when (requestMethod) {
                REQUEST_POST, REQUEST_PUT -> {
                    if (requestBody != null) {
                        httpURLConnection.doInput = true
                        val outputStream = httpURLConnection.outputStream
                        outputStream.write(requestBody.toString().toByteArray())
                        outputStream.close()
                    }
                    httpURLConnection.connect()
                }
                else -> httpURLConnection.connect()
            }

            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                if (inputStream != null) {
                    val inputStreamReader = InputStreamReader(inputStream)
                    val bufferedReader = BufferedReader(inputStreamReader)
                    val stringBuilder = StringBuilder()
                    var singleLine = bufferedReader.readLine()
                    while (singleLine != null) {
                        stringBuilder.append(singleLine)
                        singleLine = bufferedReader.readLine()
                    }
                    httpResult = stringBuilder.toString()
                }
            } else {
                throw IOException("Connection failed ($responseCode)")
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            httpURLConnection?.disconnect()
        }
        return httpResult
    }

    fun httpImageRequest(url: String): Bitmap? {
        var httpImageResult: Bitmap? = null
        var inputStream: InputStream? = null
        var httpURLConnection: HttpURLConnection? = null

        try {
            val urlObject = URL(url)
            httpURLConnection = urlObject.openConnection() as HttpURLConnection
            httpURLConnection.readTimeout = READ_TIMEOUT
            httpURLConnection.connectTimeout = CONNECT_TIMEOUT
            httpURLConnection.connect()
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                httpImageResult = BitmapFactory.decodeStream(inputStream)
            } else {
                throw IOException("Connection failed ($responseCode)")
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            httpURLConnection?.disconnect()
        }
        return httpImageResult
    }

    fun fileParse(path: String, filename: String): String? {
        var fileParseResults: String? = null
        var file: File? = null
        var fileInputStream: FileInputStream? = null
        var inputStreamReader: InputStreamReader? = null
        var bufferedReader: BufferedReader? = null

        try {
            file = File(path, filename)
            val ex = file.exists()
            fileInputStream = FileInputStream(file)
            inputStreamReader = InputStreamReader(fileInputStream)
            bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            fileParseResults = bufferedReader.readLine()
            while (fileParseResults != null) {
                stringBuilder.append(fileParseResults).append(System.getProperty("line.separator"))
                fileParseResults = bufferedReader.readLine()
            }
            fileParseResults = stringBuilder.toString()

            bufferedReader.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fileInputStream?.close()
                inputStreamReader?.close()
                bufferedReader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return fileParseResults
    }
}
