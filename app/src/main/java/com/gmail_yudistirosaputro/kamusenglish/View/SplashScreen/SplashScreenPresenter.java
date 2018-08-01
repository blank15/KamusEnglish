package com.gmail_yudistirosaputro.kamusenglish.View.SplashScreen;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.gmail_yudistirosaputro.kamusenglish.Database.KamusEngHelper;
import com.gmail_yudistirosaputro.kamusenglish.Database.KamusIdHelper;
import com.gmail_yudistirosaputro.kamusenglish.Helper.BaseInterface;
import com.gmail_yudistirosaputro.kamusenglish.Model.BahasaModel;
import com.gmail_yudistirosaputro.kamusenglish.R;
import com.gmail_yudistirosaputro.kamusenglish.Util.AppPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashScreenPresenter {
    SplashScreenInterface splashInterface;
    Context context;
    KamusIdHelper kamusIdHelper;
    KamusEngHelper kamusEngHelper;

    public SplashScreenPresenter(SplashScreenInterface splashInterface, Context context) {
        this.splashInterface = splashInterface;
        this.context = context;
        kamusEngHelper = new KamusEngHelper(context);
        kamusIdHelper = new KamusIdHelper(context);
    }
    public void loadFirstData(){
        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadData.class.getSimpleName();

        AppPreference appPreference;
        int progress;
        double maxprogress = 100;

        @Override
        protected void onPreExecute() {

            appPreference = new AppPreference(context);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreference.getFirstRun();
            if (firstRun) {

                ArrayList<BahasaModel> bahasaModels = preLoadRawEng();
                ArrayList<BahasaModel> bahasaModels2 = preLoadRawID();
                kamusEngHelper.open();
                kamusIdHelper.open();

                progress = 30;
                publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress) / bahasaModels.size();

                kamusEngHelper.beginTransaction();

                try {
                    for (BahasaModel model : bahasaModels) {
                        kamusEngHelper.insert(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                        splashInterface.setProgressbar(progress);
                    }
                    kamusEngHelper.setTransactionSuccess();
                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: Exception");
                }
                kamusEngHelper.endTransaction();

                kamusEngHelper.close();

                kamusIdHelper.beginTransaction();
                try {
                    for (BahasaModel model : bahasaModels2) {
                        kamusIdHelper.insert(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                    kamusIdHelper.setTransactionSuccess();
                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: Exception");
                }
                kamusIdHelper.endTransaction();

                kamusIdHelper.close();

                appPreference.setFirstRun(false);

                publishProgress((int) maxprogress);

            } else {
                try {
                    synchronized (this) {
                        this.wait(2000);

                        publishProgress(50);

                        this.wait(2000);
                        publishProgress((int) maxprogress);
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            splashInterface.setProgressbar(100);
        }
        @Override
        protected void onPostExecute(Void result) {
           splashInterface.onComplete();
        }
    }

    private ArrayList<BahasaModel> preLoadRawEng() {
        ArrayList<BahasaModel> models = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = context.getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                BahasaModel bahasaModel;

                bahasaModel = new BahasaModel(splitstr[0], splitstr[1]);
                models.add(bahasaModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }
    private ArrayList<BahasaModel> preLoadRawID() {
        ArrayList<BahasaModel> models = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = context.getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                BahasaModel bahasaModel;

                bahasaModel = new BahasaModel(splitstr[0], splitstr[1]);
                models.add(bahasaModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }
}
