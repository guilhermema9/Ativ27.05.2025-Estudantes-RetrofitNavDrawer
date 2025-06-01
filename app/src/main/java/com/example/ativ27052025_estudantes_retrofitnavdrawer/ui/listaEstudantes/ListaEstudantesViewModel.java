package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.listaEstudantes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListaEstudantesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ListaEstudantesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Lista de Todos os Estudantes");
    }

    public LiveData<String> getText() {
        return mText;
    }
}