package com.adityaladwa.letsalert.api;

import com.adityaladwa.letsalert.api.model.People;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Aditya on 23-Apr-16.
 */
public interface EndPoint {
    @POST("/api/people")
    Call<People> signUp(@Body People people);
}
