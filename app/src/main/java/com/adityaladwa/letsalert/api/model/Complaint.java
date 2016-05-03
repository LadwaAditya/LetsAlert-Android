package com.adityaladwa.letsalert.api.model;

/**
 * Created by Aditya on 03-May-16.
 */
public class Complaint {
    String name;
    String gcm;
    String department;
    String person;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
