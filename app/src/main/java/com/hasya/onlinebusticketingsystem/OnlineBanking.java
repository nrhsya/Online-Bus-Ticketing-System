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

public class OnlineBanking extends AppCompatActivity {

    //for ListView
    ListView bankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_banking);

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
        bankList = (ListView)findViewById(R.id.bankList);

        //make arrayList
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("CIMB");
        arrayList.add("Public Bank");
        arrayList.add("Hong Leong Bank");
        arrayList.add("RHB");
        arrayList.add("Bank Islam");
        arrayList.add("AmBank");
        arrayList.add("Standard Chartered Bank");
        arrayList.add("Bank Rakyat");
        arrayList.add("Affin Bank");

        //make arrayAdapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        bankList.setAdapter(arrayAdapter);

        //onClickListener
        bankList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(OnlineBanking.this, arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
                //if CIMB is clicked
                if (i==0){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.cimbclicks.com.my/clicks/#/"));
                    startActivity(intent);
                }
                //if Public Bank is clicked
                else if (i==1){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.pbebank.com/"));
                    startActivity(intent);
                }
                //if Hong Leong Bank is clicked
                else if (i==2){
                    //startActivity(new Intent(AddPaymentMethod.this,Direction2.class));
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://s.hongleongconnect.my/rib/app/fo/login?icp=hlb-en-all-header-txt-connectweb"));
                    startActivity(intent);
                }
                //if RHB is clicked
                else if (i==3){
                    //startActivity(new Intent(AddPaymentMethod.this,Direction2.class));
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://logon.rhb.com.my/"));
                    startActivity(intent);
                }
                //if Bank Islam is clicked
                else if (i==4){
                    //startActivity(new Intent(AddPaymentMethod.this,Direction2.class));
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.bankislam.biz/"));
                    startActivity(intent);
                }
                //if AmBank is clicked
                else if (i==5){
                    //startActivity(new Intent(AddPaymentMethod.this,Direction2.class));
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.ambank.com.my/eng/online-banking"));
                    startActivity(intent);
                }
                //if Standard Chartered Bank is clicked
                else if (i==6){
                    //startActivity(new Intent(AddPaymentMethod.this,Direction2.class));
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://retail.sc.com/my/nfs/login.htm?intcid=p-s-online-banking-login&_ga=2.155041854.764071517.1653113852-1742998562.1653113852"));
                    startActivity(intent);
                }
                //if Bank Rakyat is clicked
                else if (i==7){
                    //startActivity(new Intent(AddPaymentMethod.this,Direction2.class));
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.bankrakyat.com.my/"));
                    startActivity(intent);
                }
                //if Affin Bank is clicked
                else{
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://rib.affinalways.com/retail/#!/login"));
                    startActivity(intent);
                }
            }
        });

        //when the Back button is clicked
        ImageView back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnlineBanking.this,Topup.class));
                //Toast.makeText(PaymentMainpage.this, "This ambulance is ACTIVE!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}