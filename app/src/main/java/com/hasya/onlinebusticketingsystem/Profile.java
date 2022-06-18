package com.hasya.onlinebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {
    //declare button
    private Button move;
    //end declare button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //move code
        move = findViewById(R.id.Move);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, EditProfile.class);
                startActivity(intent);
            }
        });
        //end of move code
    }
}