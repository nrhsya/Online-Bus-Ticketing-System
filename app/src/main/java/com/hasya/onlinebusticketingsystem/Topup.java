package com.hasya.onlinebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Topup extends AppCompatActivity {

    //for ListView
    ListView topupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

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

        //listview
        topupList = (ListView)findViewById(R.id.topupList);

        //make arrayList
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Cards");
        arrayList.add("Bank Account");
        arrayList.add("Online Banking");

        //make arrayAdapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        topupList.setAdapter(arrayAdapter);

        //onClickListener
        topupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Topup.this, arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
                //if Cards is clicked
                if (i==0){
                    startActivity(new Intent(Topup.this,Cards.class));
                }
                //if Bank Account is clicked
                else if (i==1){
                    startActivity(new Intent(Topup.this,BankAccount.class));
                }
                //if Online Banking is clicked
                else{
                    startActivity(new Intent(Topup.this,OnlineBanking.class));
                }
            }
        });

        //when the Back button is clicked
        ImageView back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Topup.this,AddPaymentMethod.class));
                //Toast.makeText(PaymentMainpage.this, "This ambulance is ACTIVE!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}