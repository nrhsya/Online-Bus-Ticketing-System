package com.hasya.onlinebusticketingsystem;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OnlineBusTicketingSystem {
    private DatabaseReference databaseReference;
    public OnlineBusTicketingSystem()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(BusDetails.class.getSimpleName());
    }

    public Task<Void> add(BusDetails bus)
    {
        return databaseReference.push().setValue(bus);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }
}
