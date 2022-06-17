package com.hasya.onlinebusticketingsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BookingHistoryView extends AppCompatActivity {

    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history_view);

        recyclerView = (RecyclerView)findViewById(R.id.historyrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BookingHistoryModel>options =
                new FirebaseRecyclerOptions.Builder<BookingHistoryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BusDetails"), BookingHistoryModel.class)
                        .build();

        historyAdapter = new HistoryAdapter(options);
        recyclerView.setAdapter(historyAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        historyAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        historyAdapter.startListening();
    }



}