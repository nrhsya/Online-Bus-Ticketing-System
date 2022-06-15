package com.hasya.onlinebusticketingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        final EditText edit_boarding = findViewById(R.id.edit_boarding);
        final EditText edit_dropping = findViewById(R.id.edit_dropping);
        final EditText edit_date = findViewById(R.id.edit_date);
        final EditText edit_time = findViewById(R.id.edit_time);
        final EditText edit_bus = findViewById(R.id.edit_bus);
        final EditText edit_seatNo = findViewById(R.id.edit_seatNo);
        final EditText edit_ticketNo = findViewById(R.id.edit_ticketNo);
        final EditText edit_fare = findViewById(R.id.edit_fare);
        Button btn = findViewById(R.id.btn_submit);

        OnlineBusTicketingSystem online = new OnlineBusTicketingSystem();
        btn.setOnClickListener(v->
        {
            BusDetails bus = new BusDetails(edit_boarding.getText().toString(),edit_dropping.getText().toString(),edit_date.getText().toString(),edit_time.getText().toString(),edit_bus.getText().toString(),edit_seatNo.getText().toString(),edit_ticketNo.getText().toString(),edit_fare.getText().toString());
            online.add(bus).addOnSuccessListener(suc->
            {
                startActivity(new Intent(AddPage.this,bookinglist.class));
                Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(er->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });


        });
    }
}