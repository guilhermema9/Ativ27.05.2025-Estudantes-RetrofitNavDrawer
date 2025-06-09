package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.listaEstudantes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.repository.EstudanteRepository;

import java.util.List;

public class ListaEstudantesViewModel extends ViewModel {

    private EstudanteRepository estudanteRepository;
    private LiveData<List<Estudante>> estudantesListLiveData;

    public ListaEstudantesViewModel() {
        estudanteRepository = new EstudanteRepository();
        estudantesListLiveData = estudanteRepository.buscarTodosEstudantes();
    }

    public LiveData<List<Estudante>> getEstudantesListLiveData() {
        return estudantesListLiveData;
    }
}