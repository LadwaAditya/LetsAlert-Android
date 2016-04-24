package com.adityaladwa.letsalert.api.model;

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

    public static class Event {
        public String name;
        public String description;

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
    }
}
