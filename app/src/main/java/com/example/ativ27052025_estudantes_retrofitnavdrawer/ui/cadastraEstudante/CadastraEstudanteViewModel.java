package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.cadastraEstudante;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.repository.EstudanteRepository;

public class CadastraEstudanteViewModel extends ViewModel {

    private EstudanteRepository estudanteRepository;

    public CadastraEstudanteViewModel() {
        estudanteRepository = new EstudanteRepository();
    }

    public LiveData<Boolean> cadastraNovoEstudante(Estudante estudante){
        return estudanteRepository.cadastrarEstudante(estudante);
    }
}