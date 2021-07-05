package com.example.labinfomanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.myviewholder>{
    ArrayList<modelStudent> dataHolder;

    public studentAdapter(ArrayList<modelStudent> dataHolder) {
        this.dataHolder = dataHolder;
    }


    @NonNull
    @Override
    public studentAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  studentAdapter.myviewholder holder, int position) {
        holder.Lname.setText("Usn: "+dataHolder.get(position).getUsn());
        holder.Lslot.setText("Name: "+dataHolder.get(position).getName());
        holder.Lcount.setText("Attendance: "+dataHolder.get(position).getAttendance());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }
    class myviewholder extends RecyclerView.ViewHolder{
        TextView Lname,Lslot,Lcount;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            Lname=itemView.findViewById(R.id.labName);
            Lslot=itemView.findViewById(R.id.labslot);
            Lcount=itemView.findViewById(R.id.labclasscount);
        }
    }
}
