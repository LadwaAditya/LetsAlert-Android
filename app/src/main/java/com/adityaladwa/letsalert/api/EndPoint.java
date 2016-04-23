package com.adityaladwa.letsalert.api;

import com.adityaladwa.letsalert.api.model.People;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Aditya on 23-Apr-16.
 */
public interface EndPoint {
    @POST("/api/people")
    Call<People> signUp(@Body People people);

    @Headers("Content-Type: application/json")
    @POST("/api/people")
    Observable<People> signUpRx(@Body People people);
}
