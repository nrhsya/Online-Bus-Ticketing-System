package com.hasya.onlinebusticketingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AddPaymentMethod extends AppCompatActivity {

    //for ListView
    ListView paymentMethodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);

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

        //listview
        paymentMethodList = (ListView)findViewById(R.id.paymentMethodList);

        //make arrayList
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Top-Up Balance");
        arrayList.add("Link Bank Account");
        arrayList.add("Paypal");
        arrayList.add("Credit/Debit Card");

        //make arrayAdapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        paymentMethodList.setAdapter(arrayAdapter);

        //onClickListener
        paymentMethodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AddPaymentMethod.this, arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
                //if Topup Balance is clicked
                if (i==0){
                    startActivity(new Intent(AddPaymentMethod.this,Topup.class));
                }
                //if Link Bank Account is clicked
                else if (i==1){
                    startActivity(new Intent(AddPaymentMethod.this,BankAccount.class));
                }
                //if Paypal is clicked
                else if (i==2){
                    //startActivity(new Intent(AddPaymentMethod.this,Direction2.class));
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.paypal.com/my/signin"));
                    startActivity(intent);
                }
                //if Credit/Debit Card is clicked
                else{
                    startActivity(new Intent(AddPaymentMethod.this,Cards.class));
                }
            }
        });

        //when the Back button is clicked
        ImageView back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                startActivity(new Intent(AddPaymentMethod.this,PaymentSettings.class));
                //Toast.makeText(PaymentMainpage.this, "This ambulance is ACTIVE!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}