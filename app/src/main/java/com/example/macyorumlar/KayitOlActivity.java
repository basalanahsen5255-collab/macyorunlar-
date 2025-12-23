package com.example.macyorumlar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class KayitOlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // --- OTOMATİK GİRİŞ KONTROLÜ (EN ÜSTTE OLMALI) ---
        SharedPreferences sharedPref = getSharedPreferences("KullaniciVerileri", Context.MODE_PRIVATE);
        boolean girisYapildiMi = sharedPref.getBoolean("giris_yapildi", false);

        if (girisYapildiMi) {
            // Eğer kullanıcı daha önce giriş yapmışsa, bekleme yapma direkt Ana Sayfaya git
            startActivity(new Intent(KayitOlActivity.this, MainActivity.class));
            finish(); // Bu sayfayı kapat ki geri tuşuna basınca kayıt sayfası gelmesin
            return; // Kodun geri kalanını çalıştırma
        }
        // ------------------------------------------------

        setContentView(R.layout.activity_kayit_ol);

        EditText adSoyadEdit = findViewById(R.id.editKayitAdSoyad);
        EditText emailEdit = findViewById(R.id.editKayitEmail);
        EditText sifreEdit = findViewById(R.id.editKayitSifre);
        Button btnKayit = findViewById(R.id.btnKayitOl);
        TextView txtGirisGit = findViewById(R.id.txtGirisYapGit);

        btnKayit.setOnClickListener(v -> {
            String adSoyad = adSoyadEdit.getText().toString().trim();
            String email = emailEdit.getText().toString().trim();
            String sifre = sifreEdit.getText().toString().trim();

            if (!adSoyad.isEmpty() && !email.isEmpty() && !sifre.isEmpty()) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("kayitli_ad", adSoyad);
                editor.putString("kayitli_email", email);
                editor.putString("kayitli_sifre", sifre);
                editor.apply();

                Toast.makeText(this, "Kayıt Başarılı!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, GirisYapActivity.class));
                finish();
            }
        });

        txtGirisGit.setOnClickListener(v -> {
            startActivity(new Intent(this, GirisYapActivity.class));
            finish();
        });
    }
}