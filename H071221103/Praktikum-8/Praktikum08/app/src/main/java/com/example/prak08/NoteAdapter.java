package com.example.prak08;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private Context context;
    private List<Note> notesList;

    public NoteAdapter(Context context, List<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Note notes = notesList.get(position);
        holder.notes_title.setText(notes.getJudul());
        holder.notes_desc.setText(notes.getDeskripsi());

        String timestampText;
        if (notes.getCreatedAt().equals(notes.getUpdatedAt())) {
            timestampText = "Created at " + notes.getCreatedAt();
        } else {
            timestampText = "Updated at " + notes.getUpdatedAt();
        }
        holder.timestamp.setText(timestampText);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("record_id", notes.getId());
            context.startActivity(intent);
        });
    }




    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notes_title, notes_desc, timestamp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notes_title = itemView.findViewById(R.id.notes_title);
            notes_desc = itemView.findViewById(R.id.notes_desc);
            timestamp = itemView.findViewById(R.id.timestamp);
        }
    }
}