package com.hasya.onlinebusticketingsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class CardAdapter extends FirebaseRecyclerAdapter<Card, CardAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CardAdapter(@NonNull FirebaseRecyclerOptions<Card> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CardAdapter.myViewHolder holder, final int position, @NonNull Card model) {
        holder.cardNum.setText(model.getCardNum());
        holder.cvv.setText(model.getCvv());
        holder.expiryDate.setText(model.getExpiryDate());

        //when the update button is clicked
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.cardNum.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1200)
                        .create();

                //retrieve data
                View v = dialogPlus.getHolderView();

                EditText cardNum = v.findViewById(R.id.txtCardNum);
                EditText cvv = v.findViewById(R.id.txtCvv);
                EditText expiryDate = v.findViewById(R.id.txtExpDate);

                Button btnUpdate = v.findViewById(R.id.btnUpdate);

                cardNum.setText(model.getCardNum());
                cvv.setText(model.getCvv());
                expiryDate.setText(model.getExpiryDate());

                dialogPlus.show();

                //when the update button is clicked
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("cardNum",cardNum.getText().toString());
                        map.put("cvv",cvv.getText().toString());
                        map.put("expiryDate",expiryDate.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Card")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.cardNum.getContext(), "UPDATED Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.cardNum.getContext(), "! ERROR Updating !", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        //when the delete button is clicked
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.cardNum.getContext());
                builder.setTitle("Are you sure you want to DELETE this data?");
                builder.setMessage("DELETED data cannot be restored");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Card")
                                .child(getRef(position).getKey()).removeValue();

                        Toast.makeText(holder.cardNum.getContext(), "DELETED Successfully", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.cardNum.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public CardAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView cardNum, cvv, expiryDate;
        Button editBtn, deleteBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            cardNum = (TextView)itemView.findViewById(R.id.txtCardNum);
            cvv = (TextView)itemView.findViewById(R.id.txtCvv);
            expiryDate = (TextView)itemView.findViewById(R.id.txtExpDate);

            editBtn = (Button)itemView.findViewById(R.id.editBtn);
            deleteBtn = (Button)itemView.findViewById(R.id.deleteBtn);
        }
    }
}
