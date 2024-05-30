package com.example.pertemuan8;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{
//variabel kelas context mnggunaka adapter
// daftar objek Notes yang akan ditampilkan dalam RecyclerView
    private Context context;
    private List<Notes> notesList;
//konstruktor kls NotesAdapter yg menerima Context dan
// daftar catatan sbg parameter dan menginisialisasi variabel kls dgn nilai yg diberikan
    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }
//mengembalikan instance baru dari ViewHolder
    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search, parent, false);
        return new ViewHolder(view);
    }
//mengatur teks timestamp berdasarkan kondisi jika catatan dibuat atau diperbarui
//Setelah itu,menambahkan onClickListener ke setiap item untuk mengarahkan pengguna ke UpdateActivity saat diklik
    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Notes notes = notesList.get(position);
        holder.tvTitle.setText(notes.getJudul());
        holder.tvDesc.setText(notes.getDeskripsi());

        String timestampText;
        if (notes.getCreatedAt().equals(notes.getUpdatedAt())) {
            timestampText = "Created at " + notes.getCreatedAt();
        } else {
            timestampText = "Updated at " + notes.getUpdatedAt();
        }
        holder.tvTimestamp.setText(timestampText);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("record_id", notes.getId());
            context.startActivity(intent);
        });
    }



//mengembalikan jumlah total item dalam daftar catatan
    @Override
    public int getItemCount() {
        return notesList.size();
    }
// bertanggung jawab untuk menyimpan referensi ke tampilan dalam setiap item RecyclerView
// Ini juga melakukan inisialisasi tampilan dgn menggunakan item ViewfindViewById() dan menghubungkannya dgn variabel instance yg sesuai
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc, tvTimestamp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
        }
    }
}
