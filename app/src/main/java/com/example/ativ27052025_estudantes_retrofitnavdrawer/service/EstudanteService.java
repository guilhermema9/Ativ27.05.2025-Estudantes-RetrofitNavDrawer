package com.example.ativ27052025_estudantes_retrofitnavdrawer.service;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EstudanteService {

    @GET("./")
    Call<List<Estudante>> buscarTodosEstudantes();

    @GET("{id}")
    Call<Estudante> buscarEstudantePorId(@Path("id") int id);

    @POST("./")
    Call<Estudante> cadastrarEstudante(@Body Estudante estudante);

    @PUT("{id}")
    Call<Estudante> atualizarEstudante(@Path("id") int id, @Body Estudante estudante);

    @DELETE("{id}")
    Call<Void> deletarEstudante(@Path("id") int id);
}