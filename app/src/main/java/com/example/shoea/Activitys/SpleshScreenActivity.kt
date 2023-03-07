package com.example.shoea.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.shoea.MainActivity
import com.example.shoea.R

class SpleshScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 3000L // 3 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splesh_screen)
        // Hide the action bar
        supportActionBar?.hide()
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this@SpleshScreenActivity, MainActivity::class.java))

            // Close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}