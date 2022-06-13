//class for CRUD related operations

package com.hasya.onlinebusticketingsystem;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DataCard {
    private DatabaseReference databaseReference;

    public DataCard()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Card.class.getSimpleName());
    }

    //function to insert data into DB
    public Task<Void> add(Card cd)
    {
        return databaseReference.push().setValue(cd);
    }

    //function to update existing data in the DB
    public Task<Void> update(String key, HashMap<String ,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    //function to delete data from database
    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

    //function to get data from the DB (retrieve)
    public Query get(){
        return databaseReference.orderByKey();
    }
}
