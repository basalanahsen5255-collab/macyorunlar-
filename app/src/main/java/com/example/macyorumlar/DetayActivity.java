package com.example.macyorumlar;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        // 1. XML'deki TextView'ları buluyoruz
        TextView txtMac = findViewById(R.id.detayMac);
        TextView txtIsim = findViewById(R.id.detayIsim);
        TextView txtYorum = findViewById(R.id.detayYorum);

        // 2. Adapter'dan gönderdiğimiz "çantayı" (Intent) alıyoruz
        // Bu isimler ("mac", "isim", "yorum") Adapter'dakiyle birebir AYNI olmalı
        String gelenMac = getIntent().getStringExtra("mac");
        String gelenIsim = getIntent().getStringExtra("isim");
        String gelenYorum = getIntent().getStringExtra("yorum");

        // 3. Ekrana gerçek bilgileri yazdırıyoruz
        if (gelenMac != null) {
            txtMac.setText(gelenMac);
        }

        if (gelenIsim != null) {
            txtIsim.setText("Paylaşan: " + gelenIsim);
        }

        if (gelenYorum != null) {
            txtYorum.setText(gelenYorum);
        }
    }
}