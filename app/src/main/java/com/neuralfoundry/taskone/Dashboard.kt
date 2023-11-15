package com.neuralfoundry.taskone

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Dashboard : AppCompatActivity() {

    private lateinit var greetingText: TextView
    private lateinit var userName: EditText
    private lateinit var userAge: EditText
    private lateinit var submitButton: Button
    private lateinit var showPlanButton: Button

    private lateinit var sp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ACTIVITY_TAG","DashboardActivity : Currently on onCreate")
        setContentView(R.layout.activity_dashboard)
        sp = getSharedPreferences("task_one_sf", MODE_PRIVATE)
        editor = sp.edit()

        greetingText = findViewById(R.id.dbBanner)

        userName = findViewById(R.id.dbUserName)
        userAge = findViewById(R.id.dbUserAge)

        submitButton = findViewById(R.id.dbSubmitBtn)
        showPlanButton = findViewById(R.id.dbNextBtn)

        showPlanButton.visibility = INVISIBLE;

    }

    override fun onStart() {
        super.onStart()
        Log.d("ACTIVITY_TAG","DashboardActivity : Currently on onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ACTIVITY_TAG","DashboardActivity : Currently on onResume")
        var enteredUserName:String? = null
        var enteredUserAge:String? = null


        submitButton.setOnClickListener{
            enteredUserName = userName.text.toString()
            enteredUserAge = userAge.text.toString()

            if(enteredUserName.isNullOrBlank() || enteredUserAge.isNullOrBlank()){
                showPlanButton.visibility = INVISIBLE;
                Toast.makeText(this@Dashboard,"Please enter details!",Toast.LENGTH_SHORT).show()
                Log.d("STATUS_TAG","DashboardActivity : User details not provided!")
            }
            else{

                editor.apply{
                    putString("sp_user_name",enteredUserName)
                    putString("sp_user_age",enteredUserAge)
                    commit()
                }

                showPlanButton.visibility = VISIBLE;
                Log.d("STATUS_TAG","DashboardActivity : User details provided!")
                val message = "Welcome $enteredUserName"
                greetingText.text = message
                userName.text.clear()
                userAge.text.clear()
            }
        }

        showPlanButton.setOnClickListener{
            val intent = Intent(this,SubscriptionPlans::class.java)
//            val enteredUserName = userName.text.toString()
//            val enteredUserAge = userAge.text.toString()
            if(enteredUserName.isNullOrBlank()){
                enteredUserName = sp.getString("sp_user_name",null)
            }
            if(enteredUserAge.isNullOrBlank()){
                enteredUserAge = sp.getString("sp_user_age",null)
            }
            Log.d("STATUS_TAG","DashboardActivity : intent values are $enteredUserName")
            intent.putExtra("USER_NAME",enteredUserName)
            intent.putExtra("USER_AGE",enteredUserAge)

            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("ACTIVITY_TAG","DashboardActivity : Currently on onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ACTIVITY_TAG","DashboardActivity : Currently on onRestart")
    }



    override fun onStop() {
        super.onStop()
        Log.d("ACTIVITY_TAG","DashboardActivity : Currently on onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ACTIVITY_TAG","DashboardActivity : Currently on onDestroy")
    }


}