package com.gmail_yudistirosaputro.kamusenglish.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class BahasaModel implements Parcelable {
    private int Id;
    private String kata;
    private String keterangan;


    public BahasaModel(String kata, String keterangan) {
        this.kata = kata;
        this.keterangan = keterangan;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.kata);
        dest.writeString(this.keterangan);
    }

    public BahasaModel() {
    }

    private BahasaModel(Parcel in) {
        this.Id = in.readInt();
        this.kata = in.readString();
        this.keterangan = in.readString();
    }

    public static final Parcelable.Creator<BahasaModel> CREATOR = new Parcelable.Creator<BahasaModel>() {
        @Override
        public BahasaModel createFromParcel(Parcel source) {
            return new BahasaModel(source);
        }

        @Override
        public BahasaModel[] newArray(int size) {
            return new BahasaModel[size];
        }
    };
}
