package com.hasya.onlinebusticketingsystem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.searchHistory);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<BookingHistoryModel>options =
                new FirebaseRecyclerOptions.Builder<BookingHistoryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BusDetails").orderByChild("date").startAt(str).endAt(str+"~"), BookingHistoryModel.class)
                        .build();

        historyAdapter = new HistoryAdapter(options);
        historyAdapter.startListening();
        recyclerView.setAdapter(historyAdapter);
    }
}