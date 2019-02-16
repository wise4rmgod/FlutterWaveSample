package com.developer.wise4rmgod.flutterwavesample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Button button= findViewById(R.id.payment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RavePayManager(PayActivity.this)
                        .setAmount(200.00)
                        .setCountry("NG")
                        .setCurrency("NGN")
                        .setEmail("user@example.com")
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
                        .onStagingEnv(true)
//                .allowSaveCardFeature(true)
//                .withTheme(styleId)
//                .setSubAccounts(List<SubAccount>)
                        .initialize();

            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            Log.d("Verify response", "" + message);
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
               // checkoutButton.setText("Bought");
               // checkoutButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
              /**  checkoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ProductDetailsActivity.this,
                                "You have already made payment",
                                Toast.LENGTH_SHORT).show();
                    }
                });  **/
            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this,
                        "There was an error processing your payment. Please try again.",
                        Toast.LENGTH_SHORT).show();
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "Payment cancelled ", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
