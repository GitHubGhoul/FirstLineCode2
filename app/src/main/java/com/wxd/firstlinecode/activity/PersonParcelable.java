package com.wxd.firstlinecode.activity;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonParcelable implements Parcelable {

    private String name;
    private int age;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    public static final Parcelable.Creator<PersonParcelable> CREATOR = new Parcelable.Creator<PersonParcelable>(){

        @Override
        public PersonParcelable createFromParcel(Parcel source) {
            PersonParcelable parcelable = new PersonParcelable();
            parcelable.name = source.readString();
            parcelable.age = source.readInt();
            return parcelable;
        }

        @Override
        public PersonParcelable[] newArray(int size) {
            return new PersonParcelable[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
