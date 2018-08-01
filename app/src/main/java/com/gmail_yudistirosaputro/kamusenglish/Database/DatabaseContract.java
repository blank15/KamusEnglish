package com.gmail_yudistirosaputro.kamusenglish.Database;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_ENG = "tabel_english";
    static String TABLE_ID = "tabel_indonesia";

    static final class KamusColum implements BaseColumns{
        static String KATA = "kata";
        static String KETERANGAN = "keterangan";
    }
}
