package com.hasya.onlinebusticketingsystem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardVH extends RecyclerView.ViewHolder {

    public TextView txtCardNum, txtExpDate, txtCvv;

    Button editBtn, deleteBtn;

    public CardVH(@NonNull View itemView) {
        super(itemView);
        txtCardNum = itemView.findViewById(R.id.txtCardNum);
        txtExpDate = itemView.findViewById(R.id.txtExpDate);
        txtCvv = itemView.findViewById(R.id.txtCvv);

        editBtn = itemView.findViewById(R.id.editBtn);
        deleteBtn = itemView.findViewById(R.id.deleteBtn);
    }
}
