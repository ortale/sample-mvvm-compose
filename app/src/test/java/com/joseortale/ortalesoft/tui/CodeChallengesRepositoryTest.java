package com.joseortale.ortalesoft.tui;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.joseortale.ortalesoft.tui.data.repository.CodeChallengesRepository;
import com.joseortale.ortalesoft.tui.model.CodeChallenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CodeChallengesRepositoryTest {
    @Spy
    private CodeChallengesRepository repository;

    @Before
    public void setup() {
        repository = CodeChallengesRepository.getInstance();
    }

    @Test
    public void getCodeChallenge() {

    }
}
