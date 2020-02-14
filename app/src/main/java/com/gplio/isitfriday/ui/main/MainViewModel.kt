package com.gplio.isitfriday.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class MainViewModel : ViewModel() {
    val result = MutableLiveData<Result>()

    init {
        result.value = getResult()
    }

    fun tryAgain() {
        result.value = getResult()
    }

    /**
     * Look at this sweeet repository
     */
    private fun getResult(): Result {
        val c: Calendar = Calendar.getInstance()
        c.time = Date()
        var day = c.get(Calendar.DAY_OF_WEEK)


        if (Random().nextBoolean()) {
            day = Calendar.MONDAY
        } else {
            day= Calendar.FRIDAY
        }

        return if (day == Calendar.FRIDAY) {
            Result.YES
        } else {
            Result.NO
        }
    }
}