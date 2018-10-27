package com.torens.z.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder>{

    ArrayList<String> masseges;
    LayoutInflater inflater;

    public DataAdapter(Context context, ArrayList<String> masseges) {
        this.masseges = masseges;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_massage, viewGroup, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        String msg = masseges.get(i);
        holder.message.setText(msg);


    }

    @Override
    public int getItemCount() {
        return masseges.size();
    }
}
