package com.example.ativ27052025_estudantes_retrofitnavdrawer.ui.dadosEstudante;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.R;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.ActivityDadosEstudanteBinding;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.databinding.FragmentCadastraEstudanteBinding;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.model.Estudante;
import com.example.ativ27052025_estudantes_retrofitnavdrawer.service.CalculosEstudanteService;

public class DadosEstudanteActivity extends AppCompatActivity {

    private Estudante estudante;
    private DadosEstudanteViewModel viewModel;
    private TextView textViewNome, textViewIdade, textViewMedia, textViewPresenca, textViewSituacao;
    private Button buttonCadastraNota, buttonCadastraPresenca, buttonDeletarEstudante;
    private EditText editTextNota;
    private RadioGroup radioGroupPresenca;
    private ActivityDadosEstudanteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dados_estudante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityDadosEstudanteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        int idEstudante = getIntent().getIntExtra("id",0);

        viewModel = new ViewModelProvider(this).get(DadosEstudanteViewModel.class);
        viewModel.getEstudanteById(idEstudante).observe(this, e -> {
            estudante = e;
            textViewNome.setText(e.getNome());
            textViewIdade.setText(String.valueOf(e.getIdade()));
            textViewMedia.setText(CalculosEstudanteService.calculaMediaFinal(e));
            textViewPresenca.setText(CalculosEstudanteService.calculaPresenca(e));
            String situacao = CalculosEstudanteService.calculaSituacaoFinal(e);
            textViewSituacao.setText(situacao);
            if (situacao.equals("Aprovado")){
                textViewSituacao.setTextColor(ContextCompat.getColor(this,R.color.green));
            } else {
                textViewSituacao.setTextColor(ContextCompat.getColor(this,R.color.red));
            }
        });

        buttonCadastraNota.setOnClickListener(v ->{
            if (!editTextNota.getText().isEmpty()){
                Float nota = Float.valueOf(editTextNota.getText().toString());
                estudante.notas.add(nota);
                viewModel.atualizaEstudante(estudante).observe(DadosEstudanteActivity.this, respostaHttp ->{
                    if (respostaHttp){
                        Toast.makeText(DadosEstudanteActivity.this, "Nota cadastradada com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(DadosEstudanteActivity.this, "Erro ao cadastrar nota", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(DadosEstudanteActivity.this, "Digite uma nota", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(DadosEstudanteActivity.this, "Selecione uma opção de presença", Toast.LENGTH_SHORT).show();
            }
            viewModel.atualizaEstudante(estudante).observe(DadosEstudanteActivity.this, respostaHttp ->{
                if (respostaHttp){
                    Toast.makeText(DadosEstudanteActivity.this, "Presença cadastradada com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DadosEstudanteActivity.this, "Erro ao cadastrar presença", Toast.LENGTH_SHORT).show();
                }
            });
        });

        buttonDeletarEstudante.setOnClickListener(v -> {
            viewModel.deletaEstudante(estudante).observe(DadosEstudanteActivity.this, respostaHttp -> {
                if (respostaHttp){
                    Toast.makeText(DadosEstudanteActivity.this, "Estudante deletado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DadosEstudanteActivity.this, "Erro ao deletar estudante", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}