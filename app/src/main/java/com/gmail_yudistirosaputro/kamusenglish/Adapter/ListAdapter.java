package com.gmail_yudistirosaputro.kamusenglish.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail_yudistirosaputro.kamusenglish.Model.BahasaModel;
import com.gmail_yudistirosaputro.kamusenglish.R;
import com.gmail_yudistirosaputro.kamusenglish.View.DetailKamus;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<BahasaModel> bahasaModelList;
    private Context context;

    public ListAdapter(List<BahasaModel> bahasaModelList, Context context) {
        this.bahasaModelList = bahasaModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_bahasa,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.kata.setText(bahasaModelList.get(position).getKata());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailKamus.class);
                intent.putExtra("Detail",bahasaModelList.get(position));
                context.startActivity(intent);
            }
        });
    }
    public void addItem(List<BahasaModel> mData) {

        this.bahasaModelList = mData;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return bahasaModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView kata;
        LinearLayout linearLayout;
        ViewHolder(View itemView) {
            super(itemView);
            kata = itemView.findViewById(R.id.text_kata);
            linearLayout = itemView.findViewById(R.id.layoutlinear);
        }
    }
}
