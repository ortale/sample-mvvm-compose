package com.joseortale.ortalesoft.tui.data.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.joseortale.ortalesoft.tui.data.restapi.ApiEndpoints;
import com.joseortale.ortalesoft.tui.data.restapi.RetrofitClient;
import com.joseortale.ortalesoft.tui.model.CodeChallenge;
import com.joseortale.ortalesoft.tui.model.CodeChallengeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeChallengesRepository {
    private final String TAG = CodeChallengesRepository.class.getSimpleName();

    private static CodeChallengesRepository instance;

    private Context context;

    private final ApiEndpoints apiEndpoints;
    private List<CodeChallenge> codeChallenges;

    private final String USER_TEST = "g964";

    private MutableLiveData<List<CodeChallenge>> codeChallengesData;

    private CodeChallengesRepository(Context context) {
        apiEndpoints = RetrofitClient.getClient();

        this.context = context;
    }

    public static CodeChallengesRepository getInstance(Context context) {
        if (instance == null) {
            instance = new CodeChallengesRepository(context);
        }

        return instance;
    }

    public MutableLiveData<List<CodeChallenge>> getCodeChallenge() {
        apiEndpoints.getAuthoredCodeChallenge(USER_TEST).enqueue(new Callback<CodeChallengeResponse>() {
            @Override
            public void onResponse(Call<CodeChallengeResponse> call, Response<CodeChallengeResponse> response) {
                if (response.isSuccessful()) {
                    CodeChallengeResponse apiResponse = response.body();

                    if (apiResponse != null) {
                        codeChallenges = apiResponse.getCodeChallenges();

                        codeChallengesData.setValue(codeChallenges);
                    }

                    else {
                        Log.v(TAG, "Error status " + response.code());
                    }
                }

                else {
                    // TODO: offline data
                }
            }

            @Override
            public void onFailure(Call<CodeChallengeResponse> call, Throwable t) {
                // TODO: offline data
            }
        });

        return codeChallengesData;
    }
}
