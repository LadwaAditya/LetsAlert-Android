package com.adityaladwa.letsalert.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aditya on 04-May-16.
 */
public class MyEvent implements Parcelable {
    public String name;
    public String description;

    protected MyEvent(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    public MyEvent() {
    }

    public static final Creator<MyEvent> CREATOR = new Creator<MyEvent>() {
        @Override
        public MyEvent createFromParcel(Parcel in) {
            return new MyEvent(in);
        }

        @Override
        public MyEvent[] newArray(int size) {
            return new MyEvent[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }
}
