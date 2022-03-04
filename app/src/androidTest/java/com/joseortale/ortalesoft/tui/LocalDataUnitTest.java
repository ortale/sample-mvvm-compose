package com.joseortale.ortalesoft.tui;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.joseortale.ortalesoft.tui.data.roomdatabase.AppDatabase;
import com.joseortale.ortalesoft.tui.data.roomdatabase.CodeChallengeDao;
import com.joseortale.ortalesoft.tui.model.CodeChallenge;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class LocalDataUnitTest {
    private CodeChallengeDao codeChallengeDao;
    private AppDatabase appDatabase;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        codeChallengeDao = appDatabase.codeChallengeDao();;
    }

    @After
    public void closeDb() {
        appDatabase.close();
    }

    @Test
    public void dataEmpty() {
        List<CodeChallenge> codeChallengeList = codeChallengeDao.getAll();

        Assert.assertTrue(codeChallengeList.isEmpty());
    }

    @Test
    public void dataNotEmpty() {
        CodeChallenge codeChallenge = new CodeChallenge();
        codeChallenge.setId("uauauauuauauaua");
        codeChallenge.setName("Name Test");
        codeChallenge.setRank(4);
        codeChallenge.setDescription("Desc test");
        codeChallenge.setRankName("Good");

        codeChallengeDao.insertAll(codeChallenge);

        List<CodeChallenge> codeChallengeList = codeChallengeDao.getAll();
        Assert.assertTrue(!codeChallengeList.isEmpty());
    }

    @Test
    public void dataExistsById() {
        CodeChallenge codeChallenge = new CodeChallenge();
        codeChallenge.setId("thisIsAnId");
        codeChallenge.setName("Name Test");
        codeChallenge.setRank(4);
        codeChallenge.setDescription("Desc test");
        codeChallenge.setRankName("Good");

        codeChallengeDao.insertAll(codeChallenge);

        CodeChallenge codeChallengeList = codeChallengeDao.getById("thisIsAnId");
        Assert.assertNotNull(codeChallengeList);
    }

    @Test
    public void dataHasBeenDeleted() {
        CodeChallenge codeChallenge = new CodeChallenge();
        codeChallenge.setId("thisIsAnId");
        codeChallenge.setName("Name Test");
        codeChallenge.setRank(4);
        codeChallenge.setDescription("Desc test");
        codeChallenge.setRankName("Good");

        codeChallengeDao.insertAll(codeChallenge);

        CodeChallenge codeChallengeById = codeChallengeDao.getById("thisIsAnId");
        Assert.assertNotNull(codeChallengeById);

        codeChallengeDao.delete(codeChallenge);
        Assert.assertNull(codeChallenge);
    }

    @Test
    public void dataHasBeenUpdated() {
        CodeChallenge codeChallenge = new CodeChallenge();
        codeChallenge.setId("thisIsAnId");
        codeChallenge.setName("Name Test");
        codeChallenge.setRank(4);
        codeChallenge.setDescription("Desc test");
        codeChallenge.setRankName("Good");

        codeChallengeDao.insertAll(codeChallenge);

        codeChallenge = new CodeChallenge();
        codeChallenge.setId("thisIsAnId");
        codeChallenge.setName("Another Name Test");
        codeChallenge.setRank(3);
        codeChallenge.setDescription("Another Desc test");
        codeChallenge.setRankName("Ok");

        codeChallengeDao.update(codeChallenge);

        CodeChallenge codeChallengeById = codeChallengeDao.getById("thisIsAnId");

        Assert.assertNotEquals(codeChallenge, codeChallengeById);
    }
}
