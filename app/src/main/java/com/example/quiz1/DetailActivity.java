package com.example.quiz1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity{

    public static final String KEY_DATA = "ket_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvSelamat = findViewById(R.id.tvSelamat);
        TextView tvTipeMember = findViewById(R.id.tvTipeMember);
        TextView tvKodeBarang = findViewById(R.id.tvKodeBarang);
        TextView tvNamaBarang = findViewById(R.id.tvNamaBarang);
        TextView tvHargaBarang = findViewById(R.id.tvHargaBarang);
        TextView tvJumlahbarang = findViewById(R.id.tvJumlahBarang);
        TextView tvTotalHarga = findViewById(R.id.tvTotalHarga);
        TextView tvDiskonHarga = findViewById(R.id.tvDiskonHarga);
        TextView tvDiskonMember = findViewById(R.id.tvDiskonMember);
        TextView tvJumlahbayar = findViewById(R.id.tvJumlahBayar);

        Button btnShare = findViewById(R.id.btnShare);

        Detail detail;
        if (Build.VERSION.SDK_INT >= 33){
            detail = getIntent().getParcelableExtra(KEY_DATA, Detail.class);
        } else {
            detail = getIntent().getParcelableExtra(KEY_DATA);
        }

        tvSelamat.setText("Selamat Datang " + detail.getNamaPelanggan());
        tvTipeMember.setText("Tipe Member " + detail.getTipeMember());
        tvKodeBarang.setText("Kode Barang " + detail.getKodeBarang());
        tvNamaBarang.setText("Nama Barang " + detail.getNamaBarang());

        int hargaBarang = detail.hargaBarang;
        int jumlahBarang = detail.getJumlahBarang();
        tvHargaBarang.setText("Harga Barang Rp" + hargaBarang);
        tvJumlahbarang.setText("Jumlah Barang : " + jumlahBarang);

        int totalHarga = hargaBarang * jumlahBarang;

        tvTotalHarga.setText("Total Harga Barang Rp" + totalHarga);

        int diskon = 0;
        if (totalHarga > 10000000){
            diskon = 100000;
            totalHarga -= diskon;
        }
        String diskon10jt = Integer.toString(diskon);

        double diskonMember = 0;
        if (detail.getTipeMember().equals("Gold")){
            diskonMember = 0.1 * totalHarga;
            totalHarga -= diskonMember;
        } else if (detail.getTipeMember().equals("Silver")) {
            diskonMember = 0.05 * totalHarga;
            totalHarga -= diskonMember;
        } else if (detail.getTipeMember().equals("Biasa")) {
            diskonMember = 0.01 * totalHarga;
            totalHarga -= diskonMember;
        }

        int diskonMemberCard = (int) Math.round(diskonMember);
        String diskonMemberTanpaDesimal = Integer.toString(diskonMemberCard);
        tvDiskonHarga.setText("Diskon Harga Rp" + diskon10jt);
        tvDiskonMember.setText("Diskon Member Rp" + diskonMemberTanpaDesimal);
        tvJumlahbayar.setText("Jumlah Bayar Rp" + totalHarga);

        btnShare.setOnClickListener(click -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareMessage = tvSelamat.getText() +
                    "\n"+ tvTipeMember.getText() +
                    "\n" + tvKodeBarang.getText() +
                    "\n" + tvNamaBarang.getText() +
                    "\n" + tvHargaBarang.getText() +
                    "\n" + tvDiskonHarga.getText() +
                    "\n" + tvDiskonMember.getText() +
                    "\n" + tvJumlahbayar.getText();
            intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(intent, "Bagikan melalui"));
        });
    }

}