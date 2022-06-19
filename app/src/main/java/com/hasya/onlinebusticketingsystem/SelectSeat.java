package com.hasya.onlinebusticketingsystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SelectSeat extends AppCompatActivity {
    Button btn;

    // private FirebaseAuth firebaseAuth;
    //  private ProgressDialog progressDialog;
    // private DatabaseReference databaseReference;


    //  firebaseAuth= FirebaseAuth.getInstance();
    // databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_select_seat);

            btn = findViewById(R.id.btnSubmit);
       btn.setOnClickListener(v -> openPaymentMainPage());
    }

    public void openPaymentMainPage(){
        Intent intent = new Intent(this, PaymentMainpage.class);
        startActivity(intent);
    }
    }

