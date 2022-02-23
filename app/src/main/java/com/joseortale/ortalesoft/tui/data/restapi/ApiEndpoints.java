package com.joseortale.ortalesoft.tui.data.restapi;

import com.joseortale.ortalesoft.tui.model.CodeChallengeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoints {
    @GET("users/{user}/code-challenges/authored")
    Call<CodeChallengeResponse> getAuthoredCodeChallenge(@Path("user") String user);
}