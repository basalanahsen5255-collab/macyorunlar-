package com.example.macyorumlar;



import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import java.util.List;



public class MainActivity extends AppCompatActivity {



// Adapter ve Listeyi metodların dışında tanımlıyoruz ki her yerden erişebilelim

    private YorumAdapter adapter;

    private List<YorumModel> yorumListesi;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



// 1. Liste Bağlantısı

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



// 2. Veri Listesi (Başlangıç verileri)

        yorumListesi = new ArrayList<>();

        yorumListesi.add(new YorumModel("Caner", "Galatasaray - Fenerbahçe", "Derbi maçı çok heyecanlı geçecek!"));

        yorumListesi.add(new YorumModel("Sergen", "Beşiktaş - Trabzonspor", "Skor tahminim 2-1."));



// 3. Adapter Bağlantısı

        adapter = new YorumAdapter(yorumListesi);

        recyclerView.setAdapter(adapter);



// 4. Buton İşlemi

        Button btnGit = findViewById(R.id.btnYorumYapmaSayfasi);

        btnGit.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, YorumYapActivity.class);

// startActivity(intent); yerine alttakini kullanıyoruz:

            startActivityForResult(intent, 1); // 1 koduyla bu sayfadan veri beklediğimizi söylüyoruz

        });

    }



// --- BURASI YENİ: Diğer sayfadan gönderdiğimiz veriyi burada yakalıyoruz ---

    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



// Eğer YorumYapActivity'den (kod: 1) başarılı (RESULT_OK) bir sonuç geldiyse

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {



// Paketin içindeki verileri etiketleriyle (isim, mac, yorum) çıkarıyoruz

            String gelenIsim = data.getStringExtra("isim");

            String gelenMac = data.getStringExtra("mac");

            String gelenYorum = data.getStringExtra("yorum");



// Yeni bir model oluşturup listenin en başına (index: 0) ekliyoruz

            YorumModel yeniYorum = new YorumModel(gelenIsim, gelenMac, gelenYorum);

            yorumListesi.add(0, yeniYorum);



// Adapter'a "Liste değişti, ekranı tazele!" diyoruz

            adapter.notifyDataSetChanged();

        }

    }

}