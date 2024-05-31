package com.example.tugas5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<Instagram> instagrams;

    public SearchAdapter(ArrayList<Instagram> instagrams) {
        this.instagrams = instagrams;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Instagram instagram1 = instagrams.get(position);

        holder.search.setImageResource(instagram1.getFotoProfile());
        holder.username.setText(instagram1.getUsername());
        holder.name.setText(instagram1.getName());

        holder.search.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram1);
            holder.context.startActivity(intent);
        });

        holder.username.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram1);
            holder.context.startActivity(intent);
        });
        holder.name.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram1);
            holder.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return instagrams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView search;
        TextView username, name;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            search = itemView.findViewById(R.id.iv_search);
            username = itemView.findViewById(R.id.tv_username);
            name = itemView.findViewById(R.id.tv_name);
            context = itemView.getContext();
        }
    }
}
