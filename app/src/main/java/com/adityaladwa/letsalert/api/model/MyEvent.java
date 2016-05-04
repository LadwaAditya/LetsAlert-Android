package com.adityaladwa.letsalert.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aditya on 04-May-16.
 */
public class MyEvent implements Parcelable {
    public String name;
    public String description;
    public User user;

    public MyEvent() {
    }

    protected MyEvent(Parcel in) {
        name = in.readString();
        description = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeParcelable(user, flags);
    }

    public static class User implements Parcelable {
        public String department;

        protected User(Parcel in) {
            department = in.readString();
        }

        public User() {
        }

        public static final Creator<User> CREATOR = new Creator<User>() {
            @Override
            public User createFromParcel(Parcel in) {
                return new User(in);
            }

            @Override
            public User[] newArray(int size) {
                return new User[size];
            }
        };

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(department);
        }
    }

}
