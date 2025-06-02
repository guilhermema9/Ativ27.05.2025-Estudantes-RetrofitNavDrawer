package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.estatistica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.adapter.EstudantesAdapter;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentEstatisticaBinding;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.service.CalculosEstudanteService;

import java.util.List;

public class EstatisticaFragment extends Fragment {

    private FragmentEstatisticaBinding binding;
    private TextView textViewMediaGeral, textViewAlunoMaiorNota, textViewAlunoMenorNota, textViewMediaIdadeTurma;
    private RecyclerView recyclerViewAlunosAprovados, recyclerViewAlunosReprovados;
    private EstudantesAdapter adapterAprovados, adapterReprovados;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EstatisticaViewModel estatisticaViewModel = new ViewModelProvider(this).get(EstatisticaViewModel.class);

        binding = FragmentEstatisticaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textViewMediaGeral = binding.textViewMediaGeral;
        textViewAlunoMaiorNota = binding.textViewAlunoMaiorNota;
        textViewAlunoMenorNota = binding.textViewAlunoMenorNota;
        textViewMediaIdadeTurma = binding.textViewMediaIdadeTurma;
        recyclerViewAlunosAprovados = binding.recyclerViewAlunosAprovados;
        recyclerViewAlunosReprovados = binding.recyclerViewAlunosReprovados;

        estatisticaViewModel.getEstudantesListaCompletaLiveData().observe(getViewLifecycleOwner(), estudantes -> {
            textViewMediaGeral.setText(CalculosEstudanteService.calculaMediaGeralTurma(estudantes));
            textViewAlunoMaiorNota.setText(CalculosEstudanteService.alunoMaiorNota(estudantes));
            textViewAlunoMenorNota.setText(CalculosEstudanteService.alunoMenorNota(estudantes));
            textViewMediaIdadeTurma.setText(CalculosEstudanteService.calculaMediaIdadeTurma(estudantes));
            List<String> listaEstudantesAprovados = CalculosEstudanteService.alunosAprovados(estudantes);
            List<String> listaEstudantesReprovados = CalculosEstudanteService.alunosReprovados(estudantes);

            recyclerViewAlunosAprovados.setLayoutManager(new LinearLayoutManager(getContext()));
            adapterAprovados = new EstudantesAdapter(listaEstudantesAprovados);
            recyclerViewAlunosAprovados.setAdapter(adapterAprovados);

            recyclerViewAlunosReprovados.setLayoutManager(new LinearLayoutManager(getContext()));
            adapterReprovados = new EstudantesAdapter(listaEstudantesReprovados);
            recyclerViewAlunosReprovados.setAdapter(adapterReprovados);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}