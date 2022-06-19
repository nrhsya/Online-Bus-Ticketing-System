package com.hasya.onlinebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SelectBus extends AppCompatActivity {

    public CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_bus);


        cardView = (CardView) findViewById(R.id.cardView);
        cardView.setOnClickListener(v -> openSelectSeat());
    }

    public void openSelectSeat(){
        Intent intent = new Intent(this, SelectSeat.class);
        startActivity(intent);
    }
    }