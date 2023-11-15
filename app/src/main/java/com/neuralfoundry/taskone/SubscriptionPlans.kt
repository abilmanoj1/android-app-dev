package com.neuralfoundry.taskone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class SubscriptionPlans : AppCompatActivity() {

    private var subscription: SubscriptionType = SubscriptionType.ANNUAL
    private var isPaymentMade: Boolean = FALSE;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription_plans)
        Log.d("ACTIVITY_TAG","SubscriptionPlanActivity : Currently on onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ACTIVITY_TAG","SubscriptionPlanActivity : Currently on onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ACTIVITY_TAG","SubscriptionPlanActivity : Currently on onResume")
        val userName = intent.getStringExtra("USER_NAME")
        val textView = findViewById<TextView>(R.id.spBanner)
        val message = "Plans tailored for $userName"
        textView.text = message

        var weeklyButton = findViewById<RadioButton>(R.id.spRadio1)
        var monthlyButton = findViewById<RadioButton>(R.id.spRadio2)
        var annualButton = findViewById<RadioButton>(R.id.spRadio3)

        annualButton.isChecked = true

        weeklyButton.setOnClickListener{
            subscription = SubscriptionType.WEEKLY
            monthlyButton.isChecked = false
            annualButton.isChecked = false
        }
        monthlyButton.setOnClickListener{
            subscription = SubscriptionType.MONTHLY
            annualButton.isChecked = false
            weeklyButton.isChecked = false
        }
        annualButton.setOnClickListener{
            subscription = SubscriptionType.ANNUAL
            monthlyButton.isChecked = false
            weeklyButton.isChecked = false
        }

        var statusMessage = ""

        var paymentButton = findViewById<Button>(R.id.spPaymentButton)
        var paymentStatusMessage = findViewById<TextView>(R.id.spPaymentMsg)

        paymentStatusMessage.visibility = INVISIBLE;

        paymentButton.setOnClickListener{
            isPaymentMade = TRUE;
            statusMessage = "Successfully purchased $subscription plan."
            paymentStatusMessage.visibility = VISIBLE;
            paymentStatusMessage.text = statusMessage
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("ACTIVITY_TAG","SubscriptionPlanActivity : Currently on onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ACTIVITY_TAG","SubscriptionPlanActivity : Currently on onRestart")
    }



    override fun onStop() {
        super.onStop()
        Log.d("ACTIVITY_TAG","SubscriptionPlanActivity : Currently on onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ACTIVITY_TAG","SubscriptionPlanActivity : Currently on onDestroy")
    }
}