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
                Log.d("EstudanteRepository", "Requisição bem-sucedida. Dados recebidos: " + response.body());
                dados.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Estudante>> call, Throwable t) {
                Log.e("EstudanteRepository", "Falha requisição buscar estudantes: " + t.getMessage());
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
                Log.e("EstudanteRepository", "Falha requisição buscar por Id: " + t.getMessage());
            }
        });
        return dados;
    }

    public LiveData<Boolean> cadastrarEstudante(Estudante estudante){
        MutableLiveData<Boolean> resultadoHttp = new MutableLiveData<>();

        estudanteService.cadastrarEstudante(estudante).enqueue(new Callback<Estudante>() {
            @Override
            public void onResponse(Call<Estudante> call, Response<Estudante> response) {
                if (response.isSuccessful() && response.body() !=null ){
                    resultadoHttp.setValue(true);
                } else {
                    resultadoHttp.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Estudante> call, Throwable t) {
                Log.e("EstudanteRepository", "Falha requisição cadastrar: " + t.getMessage());
                resultadoHttp.setValue(false);
            }
        });
        return resultadoHttp;
    }

    public LiveData<Estudante> atualizarEstudante(int id, Estudante estudante){
        MutableLiveData<Estudante> dados = new MutableLiveData<>();

        estudanteService.atualizarEstudante(id,estudante).enqueue(new Callback<Estudante>() {
            @Override
            public void onResponse(Call<Estudante> call, Response<Estudante> response) {
                dados.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Estudante> call, Throwable t) {
                Log.e("EstudanteRepository", "Falha requisição atualizar: " + t.getMessage());
            }
        });
        return dados;
    }

    public LiveData<Boolean> deletarEstudante(int id){
        MutableLiveData<Boolean> dados = new MutableLiveData<>();

        estudanteService.deletarEstudante(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dados.setValue(true);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("EstudanteRepository", "Falha requisição deletar: " + t.getMessage());
            }
        });
        return dados;
    }
}
