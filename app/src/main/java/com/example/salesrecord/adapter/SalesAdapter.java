package com.example.salesrecord.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesrecord.R;
import com.example.salesrecord.activities.SalesManager;

import java.util.ArrayList;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.ViewHolder> {
    private Context myContext;
    private final ArrayList buyerNameList, valueList, idList;

    public SalesAdapter(Context context, ArrayList buyers, ArrayList values, ArrayList ids) {
        this.myContext = context;
        this.buyerNameList = buyers;
        this.valueList = values;
        this.idList = ids;
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
        holder.idItemList.setText(String.valueOf(idList.get(position)));
    }

    @Override
    public int getItemCount() {
        return buyerNameList.size();
    }

    public void clearData() {
        buyerNameList.clear();
        valueList.clear();
        idList.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView buyerName, salesPrice, idItemList;

        ViewHolder(View itemView) {
            super(itemView);
            buyerName = itemView.findViewById(R.id.buyer_name_item);
            salesPrice = itemView.findViewById(R.id.sale_value_item);
            idItemList = itemView.findViewById(R.id.id_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent saleIntent = new Intent(myContext, SalesManager.class);
            saleIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            saleIntent.putExtra("id", String.valueOf(idList.get(getAdapterPosition())));
            myContext.startActivity(saleIntent);
        }
    }
}
