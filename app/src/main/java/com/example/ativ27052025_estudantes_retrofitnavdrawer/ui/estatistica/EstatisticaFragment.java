package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.estatistica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentEstatisticaBinding;

public class EstatisticaFragment extends Fragment {

    private FragmentEstatisticaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EstatisticaViewModel estatisticaViewModel = new ViewModelProvider(this).get(EstatisticaViewModel.class);

        binding = FragmentEstatisticaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        estatisticaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}