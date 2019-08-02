package com.badoo.reaktive.sample.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.observableTimer
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.observable.startWithValue
import com.badoo.reaktive.observable.subscribe
import com.badoo.reaktive.scheduler.computationScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)

        findViewById<Button>(R.id.button).setOnClickListener {
            observableTimer(1000L, computationScheduler)
                .map { SimpleDateFormat.getDateTimeInstance().format(Date()) }
                .observeOn(mainScheduler)
                .startWithValue("Loading...")
                .subscribe(onNext = textView::setText)
        }
    }
}
