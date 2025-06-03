package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.listaEstudantes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.adapter.EstudantesAdapter;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentListaEstudantesBinding;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.dadosEstudante.DadosEstudanteActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaEstudantesFragment extends Fragment {

    private FragmentListaEstudantesBinding binding;
    private ArrayAdapter<String> adapterEstudantes;
    private EstudantesAdapter adapter;
    private RecyclerView recyclerViewNomes;
    private ListaEstudantesViewModel viewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListaEstudantesViewModel listaEstudantesViewModel = new ViewModelProvider(this).get(ListaEstudantesViewModel.class);

        binding = FragmentListaEstudantesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewNomes = binding.recyclerViewNome;

        viewModel = new ListaEstudantesViewModel();
        viewModel.getEstudantesListLiveData().observe(getViewLifecycleOwner(),estudantes ->{
            if (estudantes != null && !estudantes.isEmpty()){
                List<String> listaEstudantes = new ArrayList<>();
                for (Estudante e : estudantes){
                    listaEstudantes.add(e.getNome());
                }
                recyclerViewNomes.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new EstudantesAdapter(listaEstudantes);

                adapter.setOnItemClickListener(new EstudantesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), DadosEstudanteActivity.class);
                        intent.putExtra("id",estudantes.get(position).id);
                        startActivity(intent);
                    }
                });
                recyclerViewNomes.setAdapter(adapter);
            } else {
                Log.e("ListaEstudantesFragment", "Lista de estudantes nula");
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getEstudantesListLiveData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}