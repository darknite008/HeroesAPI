package com.om.heroesapi.heroesapi;

import com.om.heroesapi.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

@POST("users/signup")
Call<Void> register (@Body User user);

@POST("users/login")
    Call<Void> login(@Body User user);

}
