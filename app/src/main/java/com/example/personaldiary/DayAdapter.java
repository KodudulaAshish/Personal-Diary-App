package com.example.personaldiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personaldiary.model.Day;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<dayViewholder>{
    Context context;
    List<Day> day;
    DayClickListener listener;

    public DayAdapter(Context context, List<Day> day, DayClickListener listener) {
        this.context = context;
        this.day = day;
        this.listener = listener;
    }

    public DayAdapter(MainActivity context, List<Day> days) {
    }

    @NonNull
    @Override
    public dayViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new dayViewholder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull dayViewholder holder, int position) {
        holder.textViewInList.setText(day.toString());

    }

    @Override
    public int getItemCount() {
        if (day.size()==0){
            return 0;
        }else return day.size();
    }
}
class dayViewholder extends RecyclerView.ViewHolder{
    TextView textViewInList;

    public dayViewholder(@NonNull View itemView){
        super(itemView);
    }
}