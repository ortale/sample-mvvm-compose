package com.joseortale.ortalesoft.tui.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.joseortale.ortalesoft.tui.data.repository.CodeChallengesRepository;
import com.joseortale.ortalesoft.tui.model.CodeChallenge;

import java.util.List;

public class CodeChallengesViewModel extends ViewModel {
    private final String TAG = CodeChallengesViewModel.class.getSimpleName();
    private MutableLiveData<List<CodeChallenge>> codeChallengesLiveData;
    private CodeChallengesRepository repository;

    public void init() {
        repository = CodeChallengesRepository.getInstance();
    }

    public void getAllCodeChallenges() {
        codeChallengesLiveData = repository.getCodeChallenges();
    }

    public MutableLiveData<List<CodeChallenge>> getCodeChallengesLiveData() {
        return codeChallengesLiveData;
    }
}
