package com.om.heroesapi.apiurl;

import com.om.heroesapi.heroesapi.UserAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Posturl {
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    public UserAPI getInstance(){

        Retrofit retrofit=new Retrofit.Builder()
               .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAPI userAPI =retrofit.create(UserAPI.class);
        return userAPI;

    }

}
