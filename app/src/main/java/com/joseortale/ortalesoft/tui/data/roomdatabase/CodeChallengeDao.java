package com.joseortale.ortalesoft.tui.data.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.joseortale.ortalesoft.tui.model.CodeChallenge;

import java.util.List;

@Dao
public interface CodeChallengeDao {
    @Query("SELECT * FROM CodeChallenge")
    List<CodeChallenge> getAll();

    @Query("SELECT * FROM CodeChallenge WHERE id IN (:id)")
    List<CodeChallenge> loadAllById(int id);

    @Insert
    void insertAll(CodeChallenge... codeChallenge);

    @Update
    void update(CodeChallenge codeChallenge);
}
