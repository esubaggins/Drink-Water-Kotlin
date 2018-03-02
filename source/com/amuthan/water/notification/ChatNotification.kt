package com.amuthan.water.notification

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

/**
 * Created by esu baggins on 01/03/18.
 */
class ChatNotification {

    companion object {

        val CLIQ_AUTHTOKEN: String = "**********"

        fun pushToChat(message: String) {

            val serverURL: String = "https://cliq.zoho.com/api/v2/bots/amuthan/message?authtoken=$CLIQ_AUTHTOKEN"
            val url = URL(serverURL)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.connectTimeout = 300000
            connection.connectTimeout = 300000
            connection.doOutput = true

            val postData: ByteArray = message.toByteArray(StandardCharsets.UTF_8)

            connection.setRequestProperty("charset", "utf-8")
            connection.setRequestProperty("Content-lenght", postData.size.toString())
            connection.setRequestProperty("Content-Type", "application/json")

            try {
                val outputStream: DataOutputStream = DataOutputStream(connection.outputStream)
                outputStream.write(postData)
                outputStream.flush()
            } catch (exception: Exception) {

            }

            if (connection.responseCode != HttpURLConnection.HTTP_OK && connection.responseCode != HttpURLConnection.HTTP_CREATED) {
                try {

//                val inputStream : InputStream = connection.errorStream
                    val inputStream = BufferedInputStream(connection.errorStream)
                    val reader: BufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val output: String = reader.readLine()

                    println("There was error while connecting the chat $output")
                    System.exit(0)

                } catch (exception: Exception) {
                    throw Exception("Exception while push the notification  $exception.message")
                }
            }

        }
    }
}
