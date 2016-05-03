package com.adityaladwa.letsalert.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Aditya on 24-Apr-16.
 */
public class EventList {

    private ArrayList<Event> events;

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public static class Event implements Parcelable {
        public String name;
        public String description;

        protected Event(Parcel in) {
            name = in.readString();
            description = in.readString();
        }

        public Event() {
        }

        public static final Creator<Event> CREATOR = new Creator<Event>() {
            @Override
            public Event createFromParcel(Parcel in) {
                return new Event(in);
            }

            @Override
            public Event[] newArray(int size) {
                return new Event[size];
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
}
