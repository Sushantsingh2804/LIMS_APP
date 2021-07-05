package com.example.labinfomanagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class labAdapter extends RecyclerView.Adapter<labAdapter.myviewholder>{
    ArrayList<modelLab> dataHolder;
    Context context;
    String Active_User;

    public labAdapter(ArrayList<modelLab> dataHolder, Context context, String active_User) {
        this.dataHolder = dataHolder;
        this.context = context;
        Active_User = active_User;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull labAdapter.myviewholder holder, int position) {
     holder.Lname.setText("Lab: "+dataHolder.get(position).getLab_name());
     holder.Lslot.setText("Timming: "+dataHolder.get(position).getLab_slot());
     holder.Lcount.setText("Classes: "+dataHolder.get(position).getLab_class_count());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,User_lab_info.class);
            intent.putExtra("labid",dataHolder.get(position).getLab_id());
            intent.putExtra("Active_User",Active_User);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
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
