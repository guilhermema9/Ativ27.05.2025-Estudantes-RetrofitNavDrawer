package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.listaEstudantes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.R;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.adapter.EstudantesAdapter;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentListaEstudantesBinding;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;

import java.util.ArrayList;
import java.util.List;

public class ListaEstudantesFragment extends Fragment {

    private FragmentListaEstudantesBinding binding;
    private EstudantesAdapter adapter;
    private RecyclerView recyclerViewNomes;
    private ListaEstudantesViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListaEstudantesViewModel listaEstudantesViewModel = new ViewModelProvider(this).get(ListaEstudantesViewModel.class);

        binding = FragmentListaEstudantesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewNomes = binding.recyclerViewNome;

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
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
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",estudantes.get(position).id);
                        Log.i("ListaEstudantesFragment", "ID ESTUDANTE: " + String.valueOf(estudantes.get(position).id)); // AQUI ESTA RETORNANDO 10 DO USUARIO ID 10
                        NavController navController = Navigation.findNavController(requireView());
                        navController.navigate(R.id.action_nav_estudante_to_nav_dados_estudants,bundle);
                    }
                });
                recyclerViewNomes.setAdapter(adapter);
            } else {
                Log.e("ListaEstudantesFragment", "Lista de estudantes nula");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}