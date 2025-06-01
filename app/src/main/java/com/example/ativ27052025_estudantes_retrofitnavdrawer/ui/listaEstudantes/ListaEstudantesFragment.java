package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.listaEstudantes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentListaEstudantesBinding;

public class ListaEstudantesFragment extends Fragment {

    private FragmentListaEstudantesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListaEstudantesViewModel listaEstudantesViewModel = new ViewModelProvider(this).get(ListaEstudantesViewModel.class);

        binding = FragmentListaEstudantesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textSlideshow;
        //listaEstudantesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}