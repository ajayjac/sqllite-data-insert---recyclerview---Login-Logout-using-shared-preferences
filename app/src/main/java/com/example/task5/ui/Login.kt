package com.example.task5.ui

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.task5.R
import kotlinx.coroutines.joinAll
import java.lang.System.exit
import kotlin.system.exitProcess

class Login : AppCompatActivity() {

    private var pressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //actionBar Hide
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()

        //findviewbyid
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val inputEmail = findViewById<EditText>(R.id.login_inputEmail)
        val inputPassword = findViewById<EditText>(R.id.login_inputPassword)


        val pref: SharedPreferences = getSharedPreferences("countSharedPref", MODE_PRIVATE)
        val editor = pref.edit()

        if(pref.contains("email")){

            /*startActivity(Intent(this, MainActivity::class.java))*/

            val intent = Intent(this@Login, MainActivity::class.java)
            startActivity(intent)

        }
        else {


            btnLogin.setOnClickListener {
                val editEmail = inputEmail.text.toString()
                val editPassword = inputPassword.text.toString()
                if (editEmail.isNullOrEmpty() || editPassword.isNullOrEmpty()) {


                    Toast.makeText(this, "No data...",
                        Toast.LENGTH_SHORT).show()
                } else {

                    editor.putString("email", inputEmail.text.toString())
                    editor.putString("password", inputPassword.text.toString())
                    editor.commit()

                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

                    inputEmail.text.clear()
                    inputPassword.text.clear()

                    startActivity(Intent(this, MainActivity::class.java))
                }
//            Toast.makeText(this,"$email\n$password",Toast.LENGTH_SHORT).show()
            }

        }


    }
    override fun onBackPressed() {

        /*if (pressedTime + 2000 > System.currentTimeMillis()) {

            moveTaskToBack(true)

        } else {

            Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        pressedTime = System.currentTimeMillis()*/


        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.exit_dialoge, null)
        dialogBuilder.setView(dialogView)

        dialogBuilder.setTitle("Alert")
        dialogBuilder.setMessage("Are you sure want to exit?")
        dialogBuilder.setPositiveButton("Exit", DialogInterface.OnClickListener { _, _ ->

            Log.d(ContentValues.TAG, "Exit: success")
            Toast.makeText(applicationContext,"you exited from app", Toast.LENGTH_LONG).show()

            moveTaskToBack(true)


        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        val b = dialogBuilder.create()
        b.show()


    }
}