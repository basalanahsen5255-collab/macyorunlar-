package com.example.macyorumlar;

import android.content.Intent; // Bunu ekledik
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class YorumAdapter extends RecyclerView.Adapter<YorumAdapter.YorumViewHolder> {

    private List<YorumModel> yorumListesi;

    public YorumAdapter(List<YorumModel> yorumListesi) {
        this.yorumListesi = yorumListesi;
    }

    @NonNull
    @Override
    public YorumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yorum, parent, false);
        return new YorumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YorumViewHolder holder, int position) {
        YorumModel yorum = yorumListesi.get(position);
        holder.mac.setText(yorum.mac);
        holder.isim.setText(yorum.isim);
        holder.mesaj.setText(yorum.mesaj);

        // --- TIKLAMA OLAYI BURADA BAŞLIYOR ---
        holder.itemView.setOnClickListener(v -> {
            // 1. Yeni sayfaya gitmek için niyetimizi (Intent) belli ediyoruz
            Intent intent = new Intent(v.getContext(), DetayActivity.class);

            // 2. Tıklanan satırdaki verileri çantaya (intent) koyuyoruz
            intent.putExtra("mac", yorum.mac);
            intent.putExtra("isim", yorum.isim);
            intent.putExtra("yorum", yorum.mesaj);

            // 3. Çantayı alıp yeni sayfayı başlatıyoruz
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return yorumListesi.size();
    }

    public static class YorumViewHolder extends RecyclerView.ViewHolder {
        TextView mac, isim, mesaj;
        public YorumViewHolder(@NonNull View itemView) {
            super(itemView);
            mac = itemView.findViewById(R.id.txtItemMac);
            isim = itemView.findViewById(R.id.txtItemIsim);
            mesaj = itemView.findViewById(R.id.txtItemYorum);
        }
    }
}