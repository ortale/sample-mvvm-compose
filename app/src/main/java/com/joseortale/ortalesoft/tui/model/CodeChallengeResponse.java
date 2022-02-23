package com.joseortale.ortalesoft.tui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CodeChallengeResponse {
    @SerializedName("data")
    private List<CodeChallenge> codeChallenges;

    public List<CodeChallenge> getCodeChallenges() {
        return codeChallenges;
    }

    public void setCodeChallenges(List<CodeChallenge> codeChallenges) {
        this.codeChallenges = codeChallenges;
    }
}
