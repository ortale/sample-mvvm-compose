package com.joseortale.ortalesoft.tui.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.joseortale.ortalesoft.tui.data.restapi.ApiEndpoints;
import com.joseortale.ortalesoft.tui.data.restapi.RetrofitClient;
import com.joseortale.ortalesoft.tui.data.roomdatabase.AppDatabase;
import com.joseortale.ortalesoft.tui.data.roomdatabase.CodeChallengeDao;
import com.joseortale.ortalesoft.tui.model.CodeChallenge;
import com.joseortale.ortalesoft.tui.model.CodeChallengeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeChallengesRepository {
    private final String TAG = CodeChallengesRepository.class.getSimpleName();

    private static CodeChallengesRepository instance;

    private AppDatabase database;
    private CodeChallengeDao codeChallengeDao;

    private final ApiEndpoints apiEndpoints;
    private List<CodeChallenge> codeChallenges;

    private final String USER_TEST = "g964";
    private final String DB_NAME = "CodeChallenges";

    private MutableLiveData<List<CodeChallenge>> codeChallengesData;

    private CodeChallengesRepository(Context context) {
        apiEndpoints = RetrofitClient.getClient();

        database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
        codeChallengeDao = database.codeChallengeDao();
    }

    public static CodeChallengesRepository getInstance(Context context) {
        if (instance == null) {
            instance = new CodeChallengesRepository(context);
        }

        return instance;
    }

    private boolean doesCodeChallengeExists(CodeChallenge codeChallenge) {
        codeChallenges = codeChallengeDao.getAll();

        for (CodeChallenge existingCodeChallenge : codeChallenges) {
            if (existingCodeChallenge.getId().equals(codeChallenge.getId())) {
                return true;
            }
        }

        return false;
    }

    private void setLocalData(List<CodeChallenge> codeChallenges) {
        for (CodeChallenge codeChallenge : codeChallenges) {
            if (!doesCodeChallengeExists(codeChallenge)) {
                codeChallengeDao.insertAll(codeChallenge);
            } else {
                codeChallengeDao.update(codeChallenge);
            }
        }
    }

    public MutableLiveData<List<CodeChallenge>> getCodeChallenges() {
        codeChallengesData = new MutableLiveData<>();
        apiEndpoints.getAuthoredCodeChallenge(USER_TEST).enqueue(new Callback<CodeChallengeResponse>() {
            @Override
            public void onResponse(Call<CodeChallengeResponse> call, Response<CodeChallengeResponse> response) {
                if (response.isSuccessful()) {
                    CodeChallengeResponse apiResponse = response.body();

                    if (apiResponse != null) {
                        codeChallenges = apiResponse.getCodeChallenges();
                        codeChallengesData.setValue(codeChallenges);
                        setLocalData(codeChallenges);
                    } else {
                        Log.v(TAG, "Error status " + response.code());
                        codeChallenges = codeChallengeDao.getAll();
                        codeChallengesData.setValue(codeChallenges);
                    }
                } else {
                    codeChallenges = codeChallengeDao.getAll();
                    codeChallengesData.setValue(codeChallenges);
                }
            }

            @Override
            public void onFailure(Call<CodeChallengeResponse> call, Throwable t) {
                codeChallenges = codeChallengeDao.getAll();
                codeChallengesData.setValue(codeChallenges);
            }
        });

        return codeChallengesData;
    }
}
