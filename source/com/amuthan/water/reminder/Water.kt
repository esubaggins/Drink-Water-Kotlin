package com.amuthan.water.reminder

import com.fasterxml.jackson.module.kotlin.*

/**
 * Created by esu baggins on 01/03/18.
 */
class Water : Reminder() {

    private val quote: String = "_Hey you drink some water_"
    private val title: String = "Water"
    private val messageType = "modern-inline"
    private val messageThumbnail = "https://thumb.ibb.co/iYXFXb/water.jpg"

    private fun setProperties() {
        super.interval = 3600000
        super.message = constructContent()
    }

    private fun constructContent(): String {
        try {

            val message: MutableMap<String, Any> = mutableMapOf()
            message["text"] = quote

            val card: MutableMap<String, String> = mutableMapOf()
            card["title"] = title
            card["theme"] = messageType
            card["thumbnail"] = messageThumbnail
            message["card"] = card

            val objectMapper = jacksonObjectMapper()
            return objectMapper.writeValueAsString(message)

        } catch (e: Exception) {
            throw Exception("Exception while construct the message.  $e.message")
        }
    }

    override fun startReminder() {
        setProperties()
        super.startReminder()
    }
}