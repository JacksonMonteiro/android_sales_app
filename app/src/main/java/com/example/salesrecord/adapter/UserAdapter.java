package com.example.salesrecord.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesrecord.R;
import com.example.salesrecord.activities.UsersActivity;
import com.example.salesrecord.controller.UserDbController;
import com.example.salesrecord.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final Context myContext;
    private final Context myBaseContext;
    private final ArrayList<String> email;

    public UserAdapter(Context context, ArrayList<String> emailList, Context base) {
        this.myContext = context;
        this.myBaseContext = base;
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
        holder.email_item.setText(String.valueOf(email.get(position)));
    }

    @Override
    public int getItemCount() {
        return email.size();
    }

    public void clearData() {
        email.clear();
    }


    class ViewHolder extends RecyclerView.ViewHolder  {
        TextView email_item;
        ImageButton deleteUsr;

        ViewHolder(View itemView) {
            super(itemView);
            email_item = itemView.findViewById(R.id.email_item);
            deleteUsr = itemView.findViewById(R.id.delete_usr_btn);

            deleteUsr.setOnClickListener(view -> {
                UserDbController controller = new UserDbController(myBaseContext);
                controller.deleteUser(email_item.getText().toString());

                Intent i = new Intent(myContext, UsersActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(i);
            });
        }
    }
}
