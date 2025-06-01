package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.cadastraEstudante;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CadastraEstudanteViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CadastraEstudanteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Cadastra Estudante");
    }

    public LiveData<String> getText() {
        return mText;
    }
}