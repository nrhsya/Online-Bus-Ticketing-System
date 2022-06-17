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
//    RVAdapter adapter;
//    DataCard cd;

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

//        swipeRefreshLayout = findViewById(R.id.swipe);
//        recyclerView = findViewById(R.id.rv);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(manager);
//        adapter = new RVAdapter(this);
//        recyclerView.setAdapter(adapter);
//        cd = new DataCard();
//        loadData();
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

//    private void loadData() {
//        cd.get().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<Card> cds = new ArrayList<>();
//                for (DataSnapshot data : snapshot.getChildren()){
//                    Card cd = data.getValue(Card.class);
//                    cds.add(cd);
//                }
//                adapter.setItems(cds);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}