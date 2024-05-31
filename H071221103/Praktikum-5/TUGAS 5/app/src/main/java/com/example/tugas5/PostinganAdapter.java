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
import java.util.List;

public class PostinganAdapter extends RecyclerView.Adapter<PostinganAdapter.ViewHolder> {
    private ArrayList<Instagram> instagrams;

    public PostinganAdapter(ArrayList<Instagram> instagrams) {
        this.instagrams = instagrams;
    }

    @NonNull
    @Override
    public PostinganAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postingan, parent, false);
        return new PostinganAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostinganAdapter.ViewHolder holder, int position) {
        Instagram instagram = instagrams.get(position);

        holder.username.setText(instagram.getUsername());
        holder.name.setText(instagram.getName());
        holder.desc.setText(instagram.getDescription());
        holder.fotoProfile.setImageResource(instagram.getFotoProfile());
        holder.fotoPostingan.setImageResource(instagram.getFotoPostingan());
        holder.feed.setImageURI(instagram.getSelectedImageUri());

        holder.fotoProfile.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram);
            holder.context.startActivity(intent);
        });

        holder.name.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram);
            holder.context.startActivity(intent);
        });
        holder.username.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("instagram", instagram);
            holder.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return instagrams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, name, desc;
        ImageView fotoProfile, fotoPostingan, feed;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.TV_Username);
            name = itemView.findViewById(R.id.TV_Name);
            desc = itemView.findViewById(R.id.TV_Desc);
            fotoProfile = itemView.findViewById(R.id.IV_Profile);
            fotoPostingan = itemView.findViewById(R.id.IV_Postingan);
            feed = itemView.findViewById(R.id.IV_Postingan);
            context = itemView.getContext();
        }
    }
}
