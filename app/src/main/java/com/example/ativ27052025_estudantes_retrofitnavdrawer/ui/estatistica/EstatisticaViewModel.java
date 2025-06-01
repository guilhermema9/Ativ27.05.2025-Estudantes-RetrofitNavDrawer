package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.estatistica;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstatisticaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EstatisticaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Estatistica");
    }

    public LiveData<String> getText() {
        return mText;
    }
}