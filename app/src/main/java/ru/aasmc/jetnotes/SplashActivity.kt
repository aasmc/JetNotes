package ru.aasmc.jetnotes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        showMainActivityWithDelay()
    }

    private fun showMainActivityWithDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))

            // animate loading new activity
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish()
        }, 2000)
    }
}