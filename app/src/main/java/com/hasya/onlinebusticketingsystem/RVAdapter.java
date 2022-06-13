package com.hasya.onlinebusticketingsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.MissingResourceException;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<Card> list = new ArrayList<>();

    public RVAdapter(Context ctx){
        this.context = ctx;
    }

    public void setItems(ArrayList<Card> cd){
        list.addAll(cd);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new CardVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        CardVH vh = (CardVH) holder;
        Card cd = list.get(position);
        vh.txtCardNum.setText(cd.getCardNum());
        vh.txtExpDate.setText(cd.getExpiryDate());
        vh.txtCvv.setText(cd.getCvv());
        vh.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(vh.editBtn.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1200)
                        .create();

//                dialogPlus.show();

                View v = dialogPlus.getHolderView();

                EditText cardNum = v.findViewById(R.id.txtCardNum);
                EditText expDate = v.findViewById(R.id.txtExpDate);
                EditText cvv = v.findViewById(R.id.txtCvv);

                Button btnUpdate = v.findViewById(R.id.btnUpdate);

                cardNum.setText(cd.getCardNum());
                expDate.setText(cd.getExpiryDate());
                cvv.setText(cd.getCvv());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("cardNum",cardNum.getText().toString());
                        hashMap.put("expiryDate",expDate.getText().toString());
                        hashMap.put("cvv",cvv.getText().toString());

                        DataCard dataCD = new DataCard();
                        Card cd = new Card(cardNum.getText().toString(),expDate.getText().toString(),cvv.getText().toString());
                        dataCD.update("-N4Ls3fEtf2Gx6rPwJlI",hashMap).addOnSuccessListener(suc->{
                            //if success
                            Toast.makeText(vh.txtCardNum.getContext(), "Data is successfully UPDATED", Toast.LENGTH_SHORT).show();
                            dialogPlus.dismiss();

                        }).addOnFailureListener(er->{
                            //if failure
                            Toast.makeText(vh.txtCardNum.getContext(), "Data update FAILED"+er.getMessage(), Toast.LENGTH_SHORT).show();
                            dialogPlus.dismiss();
                        });

//                        FirebaseDatabase.getInstance().getReference().child("Card")
//                                .child(get(position).getKey()).updateChildren(HashMap)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(vh.txtCardNum.getContext(), "Data is successfully UPDATED", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new View.OnClickListener() {
//                                   @Override
//                                    public void onFailure(Exception e) {
//                                       Toast.makeText(vh.txtCardNum.getContext(), "Data Update Error", Toast.LENGTH_SHORT).show();
//                                       dialogPlus.dismiss();
//                                   }
//                                });
                    }
                });
            }
        });

//        Button deleteBtn = vh.findViewById(R.id.deleteBtn);

        vh.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(vh.txtCardNum.getContext());
                builder.setTitle("Are you sure you want to delete this data?");
                builder.setMessage("Deleted data cannot be recovered");

//                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        FirebaseDatabase.getInstance().getReference().child("Card")
//                                .child(getRef(position).getKey()).removeValue();
//                    }
//                });
//
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(vh.txtCardNum.getContext(), "Data Successfully Deleted", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
