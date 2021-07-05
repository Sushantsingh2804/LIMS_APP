package com.example.labinfomanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.myviewholder>{
    ArrayList<modelStudent> dataHolder;
    int attend;
    Context context;
    public AttendanceAdapter(ArrayList<modelStudent> dataHolder,Context context) {
        this.dataHolder = dataHolder;
        this.context=context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AttendanceAdapter.myviewholder holder, int position) {
        attend=Integer.parseInt(dataHolder.get(position).getAttendance());
        holder.Lname.setText("Usn: "+dataHolder.get(position).getUsn());
        holder.Lslot.setText("Name: "+dataHolder.get(position).getName());
        holder.Lcount.setText("Attendance: "+attend);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.setBackgroundColor(Color.parseColor("#00ff6e"));
                holder.Lcount.setText("Attendance: "+(attend+1));
                DbManager db=new DbManager(context);
                db.takeAttendance(dataHolder.get(position).getId(),attend+1);

            }
        });
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
