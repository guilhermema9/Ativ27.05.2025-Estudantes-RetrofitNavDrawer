package com.example.ativ27052025_estudantes_retrofitnavdrawer.conexao;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.service.EstudanteService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://10.0.2.2:8080/estudantes/";
    private static Retrofit retrofit;

    public static Retrofit getConexaoApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(ConexaoSSL.obterOkHttpClientInseguro())
                .build();
        return retrofit;
    }

    public static EstudanteService getApiService() {
        return getConexaoApi().create(EstudanteService.class);
    }
}