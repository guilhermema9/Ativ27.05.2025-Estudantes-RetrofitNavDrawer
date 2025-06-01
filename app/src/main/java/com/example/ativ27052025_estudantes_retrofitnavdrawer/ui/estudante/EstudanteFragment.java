package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.estudante;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentEstudanteBinding;

public class EstudanteFragment extends Fragment {

    private FragmentEstudanteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EstudanteViewModel estudanteViewModel = new ViewModelProvider(this).get(EstudanteViewModel.class);

        binding = FragmentEstudanteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        estudanteViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}