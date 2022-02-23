package com.joseortale.ortalesoft.tui.data.restapi;

import com.joseortale.ortalesoft.tui.model.CodeChallenge;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoints {
    @GET("users/{user}/code-challenges/authored")
    Call<CodeChallenge> getAuthoredCodeChallenge(@Path("user") Integer userId);
}