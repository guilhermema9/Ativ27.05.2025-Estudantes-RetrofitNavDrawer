package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.cadastraEstudante;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentCadastraEstudanteBinding;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;

public class CadastraEstudanteFragment extends Fragment {

    private EditText editTextNome, editTextIdade;
    private Button buttonCadastrar;
    private CadastraEstudanteViewModel viewModel;
    private Estudante estudante;
    private FragmentCadastraEstudanteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CadastraEstudanteViewModel cadastraEstudanteViewModel = new ViewModelProvider(this).get(CadastraEstudanteViewModel.class);

        binding = FragmentCadastraEstudanteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editTextNome = binding.editTextNome;
        editTextIdade = binding.editTextIdade;
        buttonCadastrar = binding.buttonSalvarEstudante;

        viewModel = new ViewModelProvider(this).get(CadastraEstudanteViewModel.class);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString();
                String idadeStr = editTextIdade.getText().toString();
                if (nome.isEmpty() || idadeStr.isEmpty()){
                    Toast.makeText(getContext(), "Preencha nome e idade do estudante", Toast.LENGTH_SHORT).show();
                    return;
                }
                int idade = Integer.parseInt(idadeStr);
                estudante = new Estudante(nome, idade);
                viewModel.cadastraNovoEstudante(estudante).observe(getViewLifecycleOwner(), respostaHttp ->{
                    if (respostaHttp){
                        Toast.makeText(getContext(), "Estudande cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                        // finish(); Finalizar fragment para retornar a "home"
                    } else {
                        Toast.makeText(getContext(), "Erro ao cadastrar estudante", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}