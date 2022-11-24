package com.example.task5.ui

import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task5.DatabaseHandler.DatabaseHandler
import com.example.task5.R
import com.example.task5.adapter.CustomAdapter
import com.example.task5.dataclass.EmpModelClass
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    private var pressedTime: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //actionBar
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#0458AB"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        title = "Task5- RecyclerView"

        //floating button id
        val fab: View = findViewById(R.id.fab)


        //floating button action
        fab.setOnClickListener { view ->
            val i = Intent(this, Form::class.java)
            startActivity(i)



           /* val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.insert_dialog, null)
            dialogBuilder.setView(dialogView)


            val edtName = dialogView.findViewById(R.id.updateName) as EditText
            val edtPhone = dialogView.findViewById(R.id.updatePhone) as EditText
            val edtAddress = dialogView.findViewById(R.id.updateAddress) as EditText
            val edtLocation = dialogView.findViewById(R.id.updateLocation) as EditText


            dialogBuilder.setTitle("Insert Record")
            dialogBuilder.setMessage("Enter data below")
            dialogBuilder.setPositiveButton("Insert", DialogInterface.OnClickListener { _, _ ->


                val updateName = edtName.text.toString()
                val updatePhone = edtPhone.text.toString()
                val updateAddress = edtAddress.text.toString()
                val updateLocation = edtLocation.text.toString()
                //creating the instance of DatabaseHandler class
                val databaseHandler: DatabaseHandler = DatabaseHandler(this)
                if(updatePhone.trim()!="" && updateName.trim()!="" && updateAddress.trim()!="" && updateLocation.trim()!=""){
                    //calling the updateEmployee method of DatabaseHandler class to update record
                    val status = databaseHandler.addEmployee(EmpModelClass(updateName, Integer.parseInt(updatePhone), updateAddress, updateLocation ))
                    if(status > -1){
                        Log.d(TAG, "Data: Inserted")
                        Toast.makeText(applicationContext,"record inserted", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"data cannot be blank", Toast.LENGTH_LONG).show()
                }

            })
            dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                //pass
            })
            val b = dialogBuilder.create()
            b.show()*/
        }



        //recyclerview getting isd and initialization
        recyclerView = findViewById(R.id.recyclerview1)
        recyclerView.layoutManager = LinearLayoutManager(this)


        //data added when user insert data
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()

        //creating custom ArrayAdapter
        val adapter = CustomAdapter(emp)

        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.app_bar_exit ->{
                //exitProcess(-1)

                preferences = getSharedPreferences("countSharedPref", MODE_PRIVATE )
                editor = preferences.edit()


               /* //logout logic from click
                editor.clear()
                editor.commit()

                startActivity(Intent(this, MainActivity::class.java))
                val intent = Intent(this@MainActivity, Login::class.java)
                startActivity(intent)

                finish()*/


                //dialogue builder
                val dialogBuilder = AlertDialog.Builder(this)
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.exit_dialoge, null)
                dialogBuilder.setView(dialogView)

                dialogBuilder.setTitle("Alert")
                dialogBuilder.setMessage("Are you sure want to Logout?")
                //positive
                dialogBuilder.setPositiveButton("Logout", DialogInterface.OnClickListener { _, _ ->

                    editor.clear()
                    editor.commit()

                    Log.d(TAG, "Exit: success")
                    Toast.makeText(applicationContext," User Logged Out", Toast.LENGTH_LONG).show()

                    startActivity(Intent(this, MainActivity::class.java))
                    val intent = Intent(this@MainActivity, Login::class.java)
                    startActivity(intent)

                    finish()


                })
                //negative
                dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                val b = dialogBuilder.create()
                b.show()

            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)


        } else {
            Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        pressedTime = System.currentTimeMillis()


        /*//dialogue to exit to Home screen
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.exit_dialoge, null)
        dialogBuilder.setView(dialogView)

        dialogBuilder.setTitle("Alert")
        dialogBuilder.setMessage("Are you sure want to exit?")

        //positive
        dialogBuilder.setPositiveButton("Exit", DialogInterface.OnClickListener { _, _ ->

            Log.d(TAG, "Exit: success")
            Toast.makeText(applicationContext,"you exited from app", Toast.LENGTH_LONG).show()

            //homescreen exit intent
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)

        })
        //negative
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        val b = dialogBuilder.create()
        b.show()*/
    }
}
