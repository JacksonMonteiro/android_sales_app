package com.example.salesrecord.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesrecord.R;

import java.util.ArrayList;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.ViewHolder> {
    private final Context myContext;
    private final ArrayList buyerNameList;
    private final ArrayList valueList;

    public SalesAdapter(Context context, ArrayList buyers, ArrayList values) {
        this.myContext = context;
        this.buyerNameList = buyers;
        this.valueList = values;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View myView = inflater.inflate(R.layout.sale_item, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.buyerName.setText(String.valueOf(buyerNameList.get(position)));
        holder.salesPrice.setText(String.valueOf(valueList.get(position)));
    }

    @Override
    public int getItemCount() {
        return buyerNameList.size();
    }

    public void clearData() {
        buyerNameList.clear();
        valueList.clear();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView buyerName, salesPrice;

        ViewHolder(View itemView) {
            super(itemView);
            buyerName = itemView.findViewById(R.id.buyer_name_item);
            salesPrice = itemView.findViewById(R.id.sale_value_item);
        }
    }
}
