package com.gmail_yudistirosaputro.kamusenglish.View.Fragment.Indonesia;

import android.content.Context;
import android.util.Log;

import com.gmail_yudistirosaputro.kamusenglish.Database.KamusEngHelper;
import com.gmail_yudistirosaputro.kamusenglish.Database.KamusIdHelper;
import com.gmail_yudistirosaputro.kamusenglish.Model.BahasaModel;
import com.gmail_yudistirosaputro.kamusenglish.View.Fragment.English.EnglishInterface;

import java.util.ArrayList;

public class IndonesiaPresenter {
    private IndonesiaInterface indonesiaInterface;
    private Context context;
    KamusIdHelper kamusIdHelper;

    public IndonesiaPresenter(IndonesiaInterface indonesiaInterface, Context context) {
        this.indonesiaInterface = indonesiaInterface;
        this.context = context;
        kamusIdHelper = new KamusIdHelper(context);
    }

    public void cariKata(String kata){
        kamusIdHelper.open();
        ArrayList<BahasaModel> models = kamusIdHelper.getDataID(kata);

        Log.d("isi ",models.size()+" "+models.get(0).getKeterangan());
        kamusIdHelper.close();
        indonesiaInterface.setData(models);
    }
}
