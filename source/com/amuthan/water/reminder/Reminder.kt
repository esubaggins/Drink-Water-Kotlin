package com.amuthan.water.reminder

import com.amuthan.water.job.Job

/**
 * Created by esu baggins on 01/03/18.
 */
open class Reminder {

    var interval: Int = 0
    var message: String = ""

    open fun startReminder() {
        val job: Job = Job(interval, message)
        job.start()
    }
}