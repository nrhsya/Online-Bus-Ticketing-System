package com.hasya.onlinebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    PaymentAdapter paymentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        recyclerView = (RecyclerView)findViewById(R.id.rvPayment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Payment> options =
                new FirebaseRecyclerOptions.Builder<Payment>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Payment"), Payment.class)
                        .build();

        paymentAdapter = new PaymentAdapter(options);
        recyclerView.setAdapter(paymentAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        paymentAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        paymentAdapter.stopListening();
    }
}