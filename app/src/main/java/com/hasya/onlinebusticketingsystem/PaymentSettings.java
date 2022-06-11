package com.hasya.onlinebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PaymentSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_settings);

        //BOTTOM NAVIGATION BAR
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Payment selected
        bottomNavigationView.setSelectedItemId(R.id.payment);

        // Perform item selected listener
        /*bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(),BookingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        }); */

        //when the Add Payment Method button is clicked
        RelativeLayout paymentMethod = findViewById(R.id.addPaymentMethodSec);
        paymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentSettings.this,AddPaymentMethod.class));
                //Toast.makeText(PaymentMainpage.this, "This ambulance is ACTIVE!", Toast.LENGTH_SHORT).show();
            }
        });

        //when the Back button is clicked
        ImageView back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentSettings.this,PaymentMainpage.class));
                //Toast.makeText(PaymentMainpage.this, "This ambulance is ACTIVE!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}