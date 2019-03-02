package com.developer.wise4rmgod.flutterwavesample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.flutterwave.raveandroid.RaveConstants
import com.flutterwave.raveandroid.RavePayActivity
import com.flutterwave.raveandroid.RavePayManager
import java.util.HashMap

class KotlinPaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_payment)

        RavePayManager(this@KotlinPaymentActivity)
                .setAmount(200.00)
                .setCountry("NG")
                .setCurrency("NGN")
                .setEmail("wise4rmgod@gmail.com")
                .setfName("Wisdom")
                .setlName("Nwokocha")
                .setNarration("Narration")
                .setPublicKey("Type your flutterwave public key")
                .setEncryptionKey(" Type your flutterwave encryptionkey here ")
                .setTxRef("Transaction reference")
                .acceptCardPayments(true)
                .acceptMpesaPayments(false)
                .acceptGHMobileMoneyPayments(false)
                .acceptAccountPayments(true)
                .onStagingEnv(false)
                //                .allowSaveCardFeature(true)
                //                .withTheme(styleId)
                //                .setSubAccounts(List<SubAccount>)
                .initialize()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            val message = data.getStringExtra("response")
            Log.d("Verify response", "" + message)
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {



            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(
                        this,
                        "There was an error processing your payment. Please try again.",
                        Toast.LENGTH_SHORT
                ).show()
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "Payment cancelled ", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
