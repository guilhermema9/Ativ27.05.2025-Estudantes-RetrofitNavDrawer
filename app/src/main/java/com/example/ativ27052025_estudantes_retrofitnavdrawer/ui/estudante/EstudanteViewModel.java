package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.estudante;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstudanteViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EstudanteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Listar Estudante");
    }

    public LiveData<String> getText() {
        return mText;
    }
}