package com.example.cloudcomputinglab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Analytics وكل اكشن وافنت عشان يتتبع التطبيق وكل ضغطة فيه
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance()
            .token.addOnCompleteListener { //عشان اجيب التوكين ونوتيفيكيشن لجوال معب
                //Toast.makeText(this, it.result.toString(), Toast.LENGTH_LONG).show()
                Log.d("Token", it.result.toString())
            }

        analytics = Firebase.analytics
        // بدو 24 ساعة عشان يصل عندك الافنت



        btn1.setOnClickListener {
            selectContentEvent("image1", "ImageView", "image")
        }

        btn2.setOnClickListener {
            trackScreenEvent()
        }

    }

    fun selectContentEvent(id: String, name: String, contentType: String) {
        //بتاخد الحدث اللي بدو يعمله المستخد
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
            param(FirebaseAnalytics.Param.ITEM_ID, id)
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, contentType)
        }
    }

    fun trackScreenEvent() {// عشان يتتبع السكرين
        //بتاخد الحدث اللي بدو يعمله المستخد
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "Main")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        }
    }
}