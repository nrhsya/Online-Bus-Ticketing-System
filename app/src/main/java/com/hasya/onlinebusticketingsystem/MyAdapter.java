package com.hasya.onlinebusticketingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<BusDetails> list;

    public MyAdapter(Context context, ArrayList<BusDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.booking,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        BusDetails bus = list.get(position);
        holder.boarding.setText(bus.getBoarding());
        holder.dropping.setText(bus.getDropping());
        holder.date.setText(bus.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView boarding, dropping, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            boarding = itemView.findViewById(R.id.boarding);
            dropping = itemView.findViewById(R.id.dropping);
            date = itemView.findViewById(R.id.date);
        }
    }

}
