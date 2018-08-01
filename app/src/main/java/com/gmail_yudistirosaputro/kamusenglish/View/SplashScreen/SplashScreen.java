package com.gmail_yudistirosaputro.kamusenglish.View.SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.gmail_yudistirosaputro.kamusenglish.Helper.BaseInterface;
import com.gmail_yudistirosaputro.kamusenglish.Model.BahasaModel;
import com.gmail_yudistirosaputro.kamusenglish.R;
import com.gmail_yudistirosaputro.kamusenglish.View.MainActivity;

import java.util.List;

public class SplashScreen extends AppCompatActivity implements SplashScreenInterface{

    private SplashScreenPresenter presenter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        presenter = new SplashScreenPresenter(this,this);
        progressBar = findViewById(R.id.progress_bar);
        presenter.loadFirstData();
    }

    @Override
    public void setProgressbar(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void onComplete() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
