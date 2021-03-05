package com.example.pomodothis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterForStats extends  RecyclerView.Adapter<MyAdapterForStats.Myholder>{
    Context context;
    ArrayList<Today> lesstats;

    MyAdapterForStats (Context cntxt, ArrayList<Today> lesstats) {
        this.context = cntxt;
        this.lesstats = lesstats;
    }


    @Override
    public int getItemCount() {
        return this.lesstats.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class Myholder extends RecyclerView.ViewHolder{
        TextView date;
        TextView stat;
        public Myholder(@NonNull View itemView)
        {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            stat=itemView.findViewById(R.id.productivity);
        }
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_stat_layout,null,false);
        Myholder h=new Myholder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        Today a=lesstats.get(position);
        //String a=lesstats.get(position);
        holder.date.setText(a.getDate());
        holder.stat.setText(String.valueOf(a.getPomos()));
    }
}
