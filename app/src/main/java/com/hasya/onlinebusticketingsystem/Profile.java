package com.hasya.onlinebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;

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
        /*start of edit text
        final EditText name = findViewById(R.id.name);
        final EditText age = findViewById(R.id.age);
        final EditText gender = findViewById(R.id.gender);
        final EditText phone = findViewById(R.id.phone);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        Button btn = findViewById(R.id.save);
        User_Program Usr_P = new User_Program();
        btn.setOnClickListener(v -> {
            User usr = new User(name.getText().toString(), age.getText().toString(), gender.getText().toString(),
                    phone.getText().toString(), email.getText().toString(), password.getText().toString());
            Usr_P.add(usr).addOnSuccessListener(suc -> {
                Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er -> {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
            //end of edit text */
    }
}