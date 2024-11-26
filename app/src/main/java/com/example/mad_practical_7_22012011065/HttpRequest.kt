package com.example.mad_practical_7_22012011065

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL

class HttpRequest {
    companion object {
        private const val TAG = "HttpRequest"
    }

    fun makeServiceCall(reqUrl: String?, token: String? = null): String? {
        var response: String? = null
        try {

            val url = URL(reqUrl)
            val conn = url.openConnection() as HttpURLConnection


            token?.let {
                conn.setRequestProperty("Authorization", "Bearer $it")
                conn.setRequestProperty("Content-Type", "application/json")
            }


            conn.requestMethod = "GET"
            conn.connect()


            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                response = convertStreamToString(conn.inputStream)
            } else {
                Log.e(TAG, "Failed to connect. Response code: ${conn.responseCode}")
            }
        } catch (e: MalformedURLException) {
            Log.e(TAG, "MalformedURLException: ${e.message}")
        } catch (e: ProtocolException) {
            Log.e(TAG, "ProtocolException: ${e.message}")
        } catch (e: IOException) {
            Log.e(TAG, "IOException: ${e.message}")
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
        }
        return response
    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        reader.forEachLine {
            stringBuilder.append(it).append("\n")
        }
        return stringBuilder.toString()
    }
}
