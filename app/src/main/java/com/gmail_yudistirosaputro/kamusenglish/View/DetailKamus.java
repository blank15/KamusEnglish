package com.gmail_yudistirosaputro.kamusenglish.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gmail_yudistirosaputro.kamusenglish.Model.BahasaModel;
import com.gmail_yudistirosaputro.kamusenglish.R;

public class DetailKamus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamus);
        TextView textViewKata = findViewById(R.id.text_kata);
        TextView textViewKet = findViewById(R.id.text_keterangan);

        BahasaModel model = getIntent().getParcelableExtra("Detail");
        textViewKet.setText(model.getKeterangan());
        textViewKata.setText(model.getKata());
    }


}
