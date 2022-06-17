package com.hasya.onlinebusticketingsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HistoryAdapter extends FirebaseRecyclerAdapter<BookingHistoryModel,HistoryAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HistoryAdapter(@NonNull FirebaseRecyclerOptions<BookingHistoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull BookingHistoryModel model) {

        holder.boarding.setText(model.getBoarding());
        holder.dropping.setText(model.getDropping());
        holder.date.setText(model.getDate());
        holder.bus.setText(model.getBus());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView boarding,dropping,date,bus;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.img1);
            boarding = (TextView)itemView.findViewById(R.id.boardingtext);
            dropping =  (TextView) itemView.findViewById(R.id.droppingtext);
            date = (TextView)itemView.findViewById(R.id.datetext);
            bus = (TextView)itemView.findViewById(R.id.bustext);
        }
    }
}
