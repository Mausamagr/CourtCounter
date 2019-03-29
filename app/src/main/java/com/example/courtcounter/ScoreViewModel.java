package com.example.courtcounter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {

    private MutableLiveData<Integer> ScoreTeamA = new MutableLiveData<>();

    public MutableLiveData<Integer> getScoreTeamA() {
        return ScoreTeamA;
    }

    public void setScoreTeamA(Integer scoreTeamA) {
        ScoreTeamA.setValue(scoreTeamA);
    }
}

