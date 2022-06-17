package com.hasya.onlinebusticketingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PerformPayment extends AppCompatActivity {

    EditText name, age, paymentTotal, paymentMethod, paymentDate;
    Button confirmPayBtn;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_payment);

        //BOTTOM NAVIGATION BAR
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Payment selected
        bottomNavigationView.setSelectedItemId(R.id.payment);

        name = (EditText)findViewById(R.id.editName);
        age = (EditText)findViewById(R.id.editAge);
        paymentTotal = (EditText)findViewById(R.id.editTotal);
        paymentMethod = (EditText)findViewById(R.id.editMethod);
        paymentDate = (EditText)findViewById(R.id.editpayDate);

        confirmPayBtn = (Button)findViewById(R.id.confirmPayBtn);
        backBtn = (ImageView)findViewById(R.id.backBtn);

        //when the Confirm Payment button is clicked
        confirmPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });

        //when the Back button is clicked
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertData() {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("age",age.getText().toString());
        map.put("paymentTotal",paymentTotal.getText().toString());
        map.put("paymentMethod",paymentMethod.getText().toString());
        map.put("paymentDate",paymentDate.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Payment").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PerformPayment.this, "Payment SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PerformPayment.this, "! Payment ERROR !", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll() {
        name.setText("");
        age.setText("");
        paymentTotal.setText("");
        paymentMethod.setText("");
        paymentDate.setText("");
    }
}