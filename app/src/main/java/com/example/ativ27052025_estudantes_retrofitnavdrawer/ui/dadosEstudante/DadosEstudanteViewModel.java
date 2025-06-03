package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.dadosEstudante;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.repository.EstudanteRepository;

public class DadosEstudanteViewModel extends ViewModel {

    private EstudanteRepository estudanteRepository;

    public DadosEstudanteViewModel(){
        estudanteRepository = new EstudanteRepository();
    }

    public LiveData<Estudante> getEstudanteById(int id){
        return estudanteRepository.buscarEstudantePorId(id);
    }

    public LiveData<Boolean> atualizaEstudante(Estudante estudante) {
        return estudanteRepository.atualizarEstudante(estudante);
    }

    public LiveData<Boolean> deletaEstudante(Estudante estudante) {
        return estudanteRepository.deletarEstudante(estudante);
    }
}