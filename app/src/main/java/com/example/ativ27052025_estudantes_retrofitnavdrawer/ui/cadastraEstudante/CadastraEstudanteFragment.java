package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.cadastraEstudante;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentCadastraEstudanteBinding;

public class CadastraEstudanteFragment extends Fragment {

    private FragmentCadastraEstudanteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CadastraEstudanteViewModel cadastraEstudanteViewModel = new ViewModelProvider(this).get(CadastraEstudanteViewModel.class);

        binding = FragmentCadastraEstudanteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        cadastraEstudanteViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}