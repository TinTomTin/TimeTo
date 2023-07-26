package com.time.timeto

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TimeToEventActivity : AppCompatActivity() {

    var textViewEventDate: TextView? = null
    var textViewEventTime: TextView? = null
    var buttonSetDate: Button? = null
    var buttonSetTime: Button? = null
    var buttonSave: Button? = null
    var cal = Calendar.getInstance()
    var TAG: String = "TimeToEventActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_to_event)

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateView(cal.time)
            }
        }

        val timeSetListener = object : TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(view: TimePicker, hour: Int, minute: Int) {
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                updateTimeView(cal.time)
            }
        }

        textViewEventDate = findViewById<TextView>(R.id.editText_EventDate)
        buttonSetDate = findViewById<Button>(R.id.button_setDate)
        textViewEventTime = findViewById(R.id.editText_EventTime)
        buttonSetTime = findViewById(R.id.button_SetTime)
        buttonSetDate!!.setOnClickListener {
            DatePickerDialog(this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()

        }

        buttonSetTime!!.setOnClickListener {
            TimePickerDialog(this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE), true).show()
        }

        buttonSave = findViewById<Button>(R.id.button_save)
        buttonSave!!.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            val eventDate = sdf.format(cal.time)
            val eventName = findViewById<TextView>(R.id.input_eventName).text.toString()
            val result  = Intent()
            result.putExtra("newEvent", eventName)
            result.putExtra("eventDate", eventDate)
            setResult(RESULT_OK, result)
            finish()
        }


    }

    private fun updateDateView(date: Date){
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
        textViewEventDate!!.text = sdf.format(date)
    }

    private fun updateTimeView(date: Date){
        val format = "HH:mm"
        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        textViewEventTime!!.text = sdf.format(date)
    }

}