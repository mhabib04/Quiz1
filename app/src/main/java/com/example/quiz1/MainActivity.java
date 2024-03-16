package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvDaftarBarang = findViewById(R.id.tvDaftarBarang);
        Button btnProses = findViewById(R.id.btnProses);
        EditText etKodeBarang = findViewById(R.id.etKodeBarang);
        EditText etNamaPelanggan = findViewById(R.id.etNamaPelanggan);
        EditText etJumlahBarang = findViewById(R.id.etJumlahBarang);
        RadioButton memberGold = findViewById(R.id.memberGold);
        RadioButton memberSilver = findViewById(R.id.memberSilver);
        RadioButton memberBiasa = findViewById(R.id.memberBiasa);

        tvDaftarBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftarBarang = new Intent(getApplicationContext(), DaftarBarang.class);
                startActivity(daftarBarang);
            }
        });

        Detail detail = new Detail();
        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaPelanggan = etNamaPelanggan.getText().toString();
                if (namaPelanggan.isEmpty()){
                    etNamaPelanggan.setError("Isi Terlebih Dahulu!");
                } else {
                    detail.setNamaPelanggan(namaPelanggan);
                }

                if (memberGold.isChecked()) {
                    detail.setTipeMember("Gold");
                } else if (memberSilver.isChecked()) {
                    detail.setTipeMember("Silver");
                } else if (memberBiasa.isChecked()) {
                    detail.setTipeMember("Biasa");
                }

                if (etKodeBarang.getText().toString().equals("SGS")){
                    detail.setKodeBarang("SGS");
                    detail.setNamaBarang("Samsung Galaxy S20");
                    detail.setHargaBarang(12999999);
                } else if (etKodeBarang.getText().toString().equals("AV4")) {
                    detail.setKodeBarang("AV4");
                    detail.setNamaBarang("ASUS Vivobook 14");
                    detail.setHargaBarang(9150999);
                } else if (etKodeBarang.getText().toString().equals("MP3")) {
                    detail.setKodeBarang("MP3");
                    detail.setNamaBarang("Macbook Pro M3");
                    detail.setHargaBarang(28999999);
                }

                String jumlah = etJumlahBarang.getText().toString();
                int jumlahBarang = Integer.parseInt(jumlah);
                if (jumlah.isEmpty()){
                    etJumlahBarang.setError("Isi Terlebih Dahulu!");
                } else {
                    detail.setJumlahBarang(jumlahBarang);
                }

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_DATA, detail);
                startActivity(intent);

            }
        });
    }
}