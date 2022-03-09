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
import com.example.salesrecord.activities.UserManagment;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context myContext;
    private final ArrayList id, email;

    public UserAdapter(Context context, ArrayList idList, ArrayList emailList) {
        this.myContext = context;
        this.id = idList;
        this.email = emailList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View myView = inflater.inflate(R.layout.user_item, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.id_item.setText(String.valueOf(id.get(position)));
        holder.email_item.setText(String.valueOf(email.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public void clearData() {
        id.clear();
        email.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id_item, email_item;

        ViewHolder(View itemView) {
            super(itemView);
            id_item = itemView.findViewById(R.id.user_id_item);
            email_item = itemView.findViewById(R.id.email_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent userIntent = new Intent(myContext, UserManagment.class);
            userIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            userIntent.putExtra("id", String.valueOf(id.get(getAdapterPosition())));
            myContext.startActivity(userIntent);
        }
    }
}
