package com.hasya.onlinebusticketingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ExistingCards extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_cards);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Card> options =
                new FirebaseRecyclerOptions.Builder<Card>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Card"), Card.class)
                        .build();

        cardAdapter = new CardAdapter(options);
        recyclerView.setAdapter(cardAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cardAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cardAdapter.stopListening();
    }
}