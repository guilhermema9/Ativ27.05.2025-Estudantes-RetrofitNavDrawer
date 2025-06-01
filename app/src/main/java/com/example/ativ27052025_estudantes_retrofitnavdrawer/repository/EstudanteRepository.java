package com.example.ativ27052025_estudantes_retrofitnavdrawer.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.conexao.ApiClient;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.service.EstudanteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstudanteRepository {

    private EstudanteService estudanteService;

    public EstudanteRepository(){
        this.estudanteService = ApiClient.getApiService();
    }

    public LiveData<List<Estudante>> buscarTodosEstudantes(){
        MutableLiveData<List<Estudante>> dados = new MutableLiveData<>();

        estudanteService.buscarTodosEstudantes().enqueue(new Callback<List<Estudante>>() {
            @Override
            public void onResponse(Call<List<Estudante>> call, Response<List<Estudante>> response) {
                dados.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Estudante>> call, Throwable t) {
                Log.e("EstudanteRepository", "Falha na requisição: " + t.getMessage());
            }
        });
        return dados;
    }

    public LiveData<Estudante> buscarEstudantePorId(int id){
        MutableLiveData<Estudante> dados = new MutableLiveData<>();

        estudanteService.buscarEstudantePorId(id).enqueue(new Callback<Estudante>() {
            @Override
            public void onResponse(Call<Estudante> call, Response<Estudante> response) {
                dados.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Estudante> call, Throwable t) {
                Log.e("EstudanteRepository", "Falha na requisição: " + t.getMessage());
            }
        });
        return dados;
    }
}
