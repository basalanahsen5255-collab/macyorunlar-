package com.example.macyorumlar;



import android.content.Intent; // Veri göndermek için ekledik

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.Toast;



import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;

import androidx.core.view.ViewCompat;

import androidx.core.view.WindowInsetsCompat;



public class YorumYapActivity extends AppCompatActivity {



    EditText editKullaniciAdi, editMacAdi, editYorumMetni;

    Button btnPaylas;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_yorum_yap);



        View rootView = findViewById(android.R.id.content);

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;

        });



        editKullaniciAdi = findViewById(R.id.editKullaniciAdi);

        editMacAdi = findViewById(R.id.editMacAdi);

        editYorumMetni = findViewById(R.id.editYorumMetni);

        btnPaylas = findViewById(R.id.btnPaylas);



        btnPaylas.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String isim = editKullaniciAdi.getText().toString();

                String mac = editMacAdi.getText().toString();

                String yorum = editYorumMetni.getText().toString();



                if (!isim.isEmpty() && !mac.isEmpty() && !yorum.isEmpty()) {



// --- BURASI KRİTİK: Veriyi ana sayfaya paketliyoruz ---

                    Intent returnIntent = new Intent();

                    returnIntent.putExtra("isim", isim);

                    returnIntent.putExtra("mac", mac);

                    returnIntent.putExtra("yorum", yorum);



// İşlem başarılı mesajı gönderiyoruz

                    setResult(RESULT_OK, returnIntent);



                    Toast.makeText(YorumYapActivity.this, "Yorum Paylaşıldı!", Toast.LENGTH_SHORT).show();



// Sayfayı kapatıp ana sayfaya geri dönüyoruz

                    finish();



                } else {

                    Toast.makeText(YorumYapActivity.this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show();

                }

            }

        });

    }

}