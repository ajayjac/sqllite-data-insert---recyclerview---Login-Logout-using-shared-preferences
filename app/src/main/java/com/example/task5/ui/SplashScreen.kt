package com.example.task5.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.task5.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        val splashtext =findViewById<TextView>(R.id.splashtext)


        splashtext.alpha=0f
        splashtext.animate().setDuration(2000).alpha(1f).withEndAction {
            val i = Intent(this, Login::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
