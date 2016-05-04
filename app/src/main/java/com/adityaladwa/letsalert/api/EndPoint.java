package com.adityaladwa.letsalert.api;

import com.adityaladwa.letsalert.api.model.Complaint;
import com.adityaladwa.letsalert.api.model.MyEvent;
import com.adityaladwa.letsalert.api.model.People;


import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Aditya on 23-Apr-16.
 */
public interface EndPoint {


    @Headers("Content-Type: application/json")
    @POST("/api/people")
    Observable<People> signUpRx(@Body People people);

    @POST("/api/complaints")
    Observable<Complaint> postComplaint(@Body Complaint complaint);

    @GET("/api/eventalertsall")
    Observable<ArrayList<MyEvent>> getEventsRx();

    @GET("/api/eventalerts/police")
    Observable<ArrayList<MyEvent>> getPoliceEventsRx();

    @GET("/api/eventalerts/electricity")
    Observable<ArrayList<MyEvent>> getElectricityEventsRx();

    @GET("/api/eventalerts/water")
    Observable<ArrayList<MyEvent>> getWaterEventsRx();

    @GET("/api/eventalerts/college")
    Observable<ArrayList<MyEvent>> getCollegeEventsRx();

}
