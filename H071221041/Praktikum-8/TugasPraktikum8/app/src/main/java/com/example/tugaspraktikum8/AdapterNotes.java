package com.example.tugaspraktikum8;

import static com.example.tugaspraktikum8.MainActivity.REQUEST_CODE_UPDATE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdapterNotes extends RecyclerView.Adapter<AdapterNotes.ViewHolder> {
    Context context;
    ArrayList<Note> notes;

    public AdapterNotes(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public AdapterNotes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotes.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_desc.setText(note.getDesc());
        String formattedTimestamp;
        if (note.isNew()) {
            formattedTimestamp = "Created at " + formatDate(note.getCreatedTimestamp());
        } else {
            formattedTimestamp = "Updated at " + formatDate(note.getUpdatedTimestamp());
        }
        holder.tv_time.setText(formattedTimestamp);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), UpdateActivity.class);
            int data = notes.get(holder.getAdapterPosition()).getId();
            String id = Integer.toString(data);
            intent.putExtra("id", id);
            intent.putExtra("title", note.getTitle());
            intent.putExtra("desc", note.getDesc());
            ((MainActivity) context).startActivityForResult(intent, REQUEST_CODE_UPDATE);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_desc, tv_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }

    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        Date date = new Date(timestamp);
        return sdf.format(date);
    }
}