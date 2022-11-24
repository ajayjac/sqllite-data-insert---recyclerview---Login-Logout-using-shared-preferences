package com.example.task5.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.task5.DatabaseHandler.DatabaseHandler
import com.example.task5.dataclass.EmpModelClass
import com.example.task5.R

class Form : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#0458AB"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        title = "Task - SQL data insert"

        val submitBtn = findViewById<Button>(R.id.submitBtn)



        submitBtn.setOnClickListener {
            val edtName = findViewById(R.id.updateName) as EditText
            val edtPhone = findViewById(R.id.updatePhone) as EditText
            val edtAddress = findViewById(R.id.updateAddress) as EditText
            val edtLocation = findViewById(R.id.updateLocation) as EditText

            val updateName = edtName.text.toString()
            val updatePhone = edtPhone.text.toString()
            val updateAddress = edtAddress.text.toString()
            val updateLocation = edtLocation.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if(updatePhone.trim()!="" && updateName.trim()!="" && updateAddress.trim()!="" && updateLocation.trim()!=""){
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler.addEmployee(EmpModelClass(updateName, updatePhone, updateAddress, updateLocation ))
                if(status > -1){
                    Log.d(TAG, "Data: Inserted")
                    Toast.makeText(applicationContext,"record inserted", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                }
            }else{
                Toast.makeText(applicationContext,"data cannot be blank", Toast.LENGTH_LONG).show()
            }

        }

    }
}