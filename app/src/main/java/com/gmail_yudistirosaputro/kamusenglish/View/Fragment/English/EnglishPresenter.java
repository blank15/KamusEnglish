package com.gmail_yudistirosaputro.kamusenglish.View.Fragment.English;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.gmail_yudistirosaputro.kamusenglish.Database.KamusEngHelper;
import com.gmail_yudistirosaputro.kamusenglish.Helper.BaseInterface;
import com.gmail_yudistirosaputro.kamusenglish.Model.BahasaModel;
import java.util.ArrayList;

public class EnglishPresenter {

    private EnglishInterface englishInterface;
    private Context context;
    KamusEngHelper kamusEngHelper;

    public EnglishPresenter(EnglishInterface englishInterface, Context context) {
        this.englishInterface = englishInterface;
        this.context = context;
        kamusEngHelper = new KamusEngHelper(context);
    }


    public void cariKata(String kata){
        kamusEngHelper.open();
        ArrayList<BahasaModel> models = kamusEngHelper.getDataEng(kata);
        Log.d("isi ",models.size()+" "+models.get(0).getKeterangan());
        kamusEngHelper.close();
        englishInterface.setData(models);
    }


}
