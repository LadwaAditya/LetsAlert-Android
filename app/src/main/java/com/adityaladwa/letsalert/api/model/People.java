package com.adityaladwa.letsalert.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aditya on 23-Apr-16.
 */
public class People implements Parcelable {

    private String name;
    private String gcm;
    private String email;
    private String phone;

    public People() {

    }

    protected People(Parcel in) {
        name = in.readString();
        gcm = in.readString();
        email = in.readString();
        phone = in.readString();
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGcm() {
        return gcm;
    }

    public void setGcm(String gcm) {
        this.gcm = gcm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gcm);
        dest.writeString(email);
        dest.writeString(phone);
    }
}
