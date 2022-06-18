package com.hasya.onlinebusticketingsystem;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

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
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull BookingHistoryModel model) {

        holder.boarding.setText(model.getBoarding());
        holder.dropping.setText(model.getDropping());
        holder.date.setText(model.getDate());
        holder.bus.setText(model.getBus());
        holder.time.setText(model.getTime());
        holder.seatNo.setText(model.getSeatNo());
        holder.ticketNo.setText(model.getTicketNo());
        holder.fare.setText(model.getFare());

        holder.btnDeleteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.boarding.getContext());
                builder.setTitle("Are you Sure ?");
                builder.setMessage("Deleted data can't be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("BusDetails")
                                .child(getRef(position).getKey()).removeValue();


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.boarding.getContext(),"Cancelled.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView boarding,dropping,date,bus,time,seatNo,ticketNo,fare;

        Button btnDeleteHistory;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.img1);
            boarding = (TextView)itemView.findViewById(R.id.boardingtext);
            dropping =  (TextView) itemView.findViewById(R.id.droppingtext);
            date = (TextView)itemView.findViewById(R.id.datetext);
            bus = (TextView)itemView.findViewById(R.id.bustext);
            time = (TextView)itemView.findViewById(R.id.timetext);
            seatNo = (TextView)itemView.findViewById(R.id.seatNotext);
            ticketNo = (TextView)itemView.findViewById(R.id.ticketNotext);
            fare = (TextView)itemView.findViewById(R.id.faretext);

            btnDeleteHistory = (Button)itemView.findViewById(R.id.btnDeleteHistory);
        }
    }
}
