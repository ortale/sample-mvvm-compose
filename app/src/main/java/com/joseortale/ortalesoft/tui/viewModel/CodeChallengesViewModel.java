package com.joseortale.ortalesoft.tui.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joseortale.ortalesoft.tui.data.repository.CodeChallengesRepository;
import com.joseortale.ortalesoft.tui.model.CodeChallenge;

import java.util.List;

public class CodeChallengesViewModel extends ViewModel {
    private final String TAG = CodeChallengesViewModel.class.getSimpleName();
    private MutableLiveData<List<CodeChallenge>> codeChallengesLiveData;
    private CodeChallengesRepository repository;

    public MutableLiveData<List<CodeChallenge>> getAllCodeChallenges() {
        repository = CodeChallengesRepository.getInstance();
        codeChallengesLiveData = repository.getCodeChallenges();

        return codeChallengesLiveData != null ? codeChallengesLiveData : new MutableLiveData<>();
    }
}
