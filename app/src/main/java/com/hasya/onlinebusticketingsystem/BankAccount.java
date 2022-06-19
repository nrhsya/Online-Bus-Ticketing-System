package com.hasya.onlinebusticketingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BankAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account);

        //BOTTOM NAVIGATION BAR
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Payment selected
        bottomNavigationView.setSelectedItemId(R.id.payment);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
//                    case R.id.booking:
//                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
                    case R.id.bookingHistory:
                        startActivity(new Intent(getApplicationContext(),BookingHistoryMainPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.payment:
                        startActivity(new Intent(getApplicationContext(),PaymentMainpage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //end BOTTOM NAVIGATION

        //when Maybank2u is clicked
        RelativeLayout maybank = findViewById(R.id.linkedBankAccSec);
        maybank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(BankAccount.this,Topup.class));
                //Toast.makeText(PaymentMainpage.this, "This ambulance is ACTIVE!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.maybank2u.com.my/home/m2u/common/login.do"));
                startActivity(intent);
            }
        });

        //when the Back button is clicked
        ImageView back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BankAccount.this,Topup.class));
                //Toast.makeText(PaymentMainpage.this, "This ambulance is ACTIVE!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}