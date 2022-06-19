/* User Program
package com.hasya.onlinebusticketingsystem;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_Program{
    private DatabaseReference databaseReference;

    public User_Program(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(User usr){
        //if(usr == null) //throw exception
        return databaseReference.push().setValue(usr);
    }
}

// End User Program */

