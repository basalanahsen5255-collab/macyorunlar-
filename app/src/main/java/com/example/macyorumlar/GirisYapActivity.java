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

public class GirisYapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);

        // XML'deki bileşenleri bağlıyoruz
        TextView txtBaslik = findViewById(R.id.txtGirisBaslik);
        EditText emailEdit = findViewById(R.id.editGirisEmail);
        EditText sifreEdit = findViewById(R.id.editGirisSifre);
        Button btnGiris = findViewById(R.id.btnGirisYap);
        TextView txtKayitGit = findViewById(R.id.txtKayitOlGit);

        // Kayıt sayfasından isim gelmiş mi kontrol et
        String gelenIsim = getIntent().getStringExtra("kullanici_adi");
        if (gelenIsim != null && !gelenIsim.isEmpty()) {
            txtBaslik.setText("Aramıza Hoş Geldin,\n" + gelenIsim + "!");
        }

        // Giriş Yap butonuna basınca
        btnGiris.setOnClickListener(v -> {
            String girilenEmail = emailEdit.getText().toString().trim();
            String girilenSifre = sifreEdit.getText().toString().trim();

            if (!girilenEmail.isEmpty() && !girilenSifre.isEmpty()) {

                // --- TELEFON HAFIZASINDAN VERİLERİ OKUYORUZ ---
                SharedPreferences sharedPref = getSharedPreferences("KullaniciVerileri", Context.MODE_PRIVATE);
                String kayitliEmail = sharedPref.getString("kayitli_email", "");
                String kayitliSifre = sharedPref.getString("kayitli_sifre", "");

                // Girilen bilgiler hafızadakiyle aynı mı?
                if (girilenEmail.equals(kayitliEmail) && girilenSifre.equals(kayitliSifre)) {

                    // --- MÜHÜRLEME BURADA: Giriş yapıldığını hafızaya kaydediyoruz ---
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("giris_yapildi", true);
                    editor.apply();

                    Toast.makeText(this, "Giriş Başarılı!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GirisYapActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Hatalı e-posta veya şifre!", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "Lütfen alanları boş bırakmayın!", Toast.LENGTH_SHORT).show();
            }
        });

        // "Hesabım yok, kayıt ol" yazısına basınca
        txtKayitGit.setOnClickListener(v -> {
            Intent intent = new Intent(GirisYapActivity.this, KayitOlActivity.class);
            startActivity(intent);
        });
    }
}