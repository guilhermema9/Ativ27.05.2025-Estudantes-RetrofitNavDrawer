package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.estatistica;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.repository.EstudanteRepository;

import java.util.List;

public class EstatisticaViewModel extends ViewModel {

    private EstudanteRepository estudanteRepository;
    private LiveData<List<Estudante>> estudantesListaCompletaLiveData;

    public EstatisticaViewModel() {
        estudanteRepository = new EstudanteRepository();
        estudantesListaCompletaLiveData = estudanteRepository.buscarTodosEstudantesComTodosOsDados();
    }

    public LiveData<List<Estudante>> getEstudantesListaCompletaLiveData() {
        return estudantesListaCompletaLiveData;
    }
}