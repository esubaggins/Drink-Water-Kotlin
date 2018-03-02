package com.amuthan.water.job

import com.amuthan.water.notification.ChatNotification

/**
 * Created by esu baggins on 01/03/18.
 */
class Job : Thread {

    var interval: Int = 0
    var message: String = ""

    constructor(interval: Int, message: String) {
        this.interval = interval
        this.message = message
    }


    override fun run() {
        try {

            while (true) {
                ChatNotification.pushToChat(message)
                print(message)
                sleep(interval.toLong())
            }
        } catch (e: Exception) {
            throw Exception("Exception while push the notification  $e.message")
        }
    }
}