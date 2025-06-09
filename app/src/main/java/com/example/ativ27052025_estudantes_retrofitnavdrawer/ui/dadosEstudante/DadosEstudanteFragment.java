package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.dadosEstudante;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.R;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentDadosEstudanteBinding;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.service.CalculosEstudanteService;

public class DadosEstudanteFragment extends Fragment {

    private Estudante estudante;
    private DadosEstudanteViewModel viewModel;
    private TextView textViewNome, textViewIdade, textViewMedia, textViewPresenca, textViewSituacao;
    private Button buttonCadastraNota, buttonCadastraPresenca, buttonDeletarEstudante;
    private EditText editTextNota;
    private RadioGroup radioGroupPresenca;
    private FragmentDadosEstudanteBinding binding;
    private int idEstudante;

    public static DadosEstudanteFragment newInstance() {
        return new DadosEstudanteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(DadosEstudanteViewModel.class);

        binding = FragmentDadosEstudanteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textViewNome = binding.textViewNome;
        textViewIdade = binding.textViewIdade;
        textViewMedia = binding.textViewMedia;
        textViewPresenca = binding.textViewPresenca;
        textViewSituacao = binding.textViewSituacao;
        buttonCadastraNota = binding.buttonCadastrarNota;
        editTextNota = binding.editTextNota;
        buttonCadastraPresenca = binding.buttonCadastrarPresenca;
        radioGroupPresenca = binding.radioGroupPresenca;
        buttonDeletarEstudante = binding.buttonDeletarEstudante;

        Log.i("DadosEstudanteFragment", "ARGUMENTS: " + getArguments()); // AQUI ESTA RETORNANDO NULL
        if (getArguments() != null){
            idEstudante = getArguments().getInt("id");
            Log.i("DadosEstudanteFragment", "ID ESTUDANTE: " + idEstudante);
        }

        viewModel = new ViewModelProvider(this).get(DadosEstudanteViewModel.class);
        viewModel.getEstudanteById(idEstudante).observe(getViewLifecycleOwner(), e -> {
            estudante = e;
            textViewNome.setText(e.getNome());
            textViewIdade.setText(String.valueOf(e.getIdade()));
            textViewMedia.setText(CalculosEstudanteService.calculaMediaFinal(e));
            textViewPresenca.setText(CalculosEstudanteService.calculaPresenca(e));
            String situacao = CalculosEstudanteService.calculaSituacaoFinal(e);
            textViewSituacao.setText(situacao);
            if (situacao.equals("Aprovado")){
                textViewSituacao.setTextColor(ContextCompat.getColor(getContext(),R.color.green));
            } else {
                textViewSituacao.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
            }
        });

        buttonCadastraNota.setOnClickListener(v ->{
            if (!editTextNota.getText().isEmpty()){
                Float nota = Float.valueOf(editTextNota.getText().toString());
                estudante.notas.add(nota);
                viewModel.atualizaEstudante(estudante).observe(getViewLifecycleOwner(), respostaHttp ->{
                    if (respostaHttp){
                        Navigation.findNavController(v).popBackStack();
                        Toast.makeText(getContext(), "Nota cadastradada com sucesso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Erro ao cadastrar nota", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getContext(), "Digite uma nota", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCadastraPresenca.setOnClickListener(v -> {
            boolean presenca = false;
            if (radioGroupPresenca.getCheckedRadioButtonId() == R.id.radioButtonPresente){
                presenca = true;
                estudante.presenca.add(presenca);
            } else if (radioGroupPresenca.getCheckedRadioButtonId() == R.id.radioButtonAusente){
                presenca = false;
                estudante.presenca.add(presenca);
            } else {
                Toast.makeText(getContext(), "Selecione uma opção de presença", Toast.LENGTH_SHORT).show();
            }
            viewModel.atualizaEstudante(estudante).observe(getViewLifecycleOwner(), respostaHttp ->{
                if (respostaHttp){
                    Navigation.findNavController(v).popBackStack();
                    Toast.makeText(getContext(), "Presença cadastradada com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Erro ao cadastrar presença", Toast.LENGTH_SHORT).show();
                }
            });
        });

        buttonDeletarEstudante.setOnClickListener(v -> {
            viewModel.deletaEstudante(estudante).observe(getViewLifecycleOwner(), respostaHttp -> {
                if (respostaHttp){
                    Navigation.findNavController(v).popBackStack();
                    Toast.makeText(getContext(), "Estudante deletado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Erro ao deletar estudante", Toast.LENGTH_SHORT).show();
                }
            });
        });
        return root;
        //return inflater.inflate(R.layout.fragment_dados_estudante, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}