package com.example.ativ27052025_estudantes_retrofitnavdrawer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ativ27052025_estudantes_retrofitnavdrawer.R;

import java.util.List;

public class EstudantesAdapter extends RecyclerView.Adapter<EstudantesAdapter.ViewHolder>{

    private List<String> estudantesList;
    private OnItemClickListener listener;

    public EstudantesAdapter(List<String> estudantesList) {
        this.estudantesList = estudantesList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_estudante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextViewNome().setText(estudantesList.get(position));
    }

    @Override
    public int getItemCount() {
        return estudantesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNomeRecyclerView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null){
                        listener.onItemClick(position);
                    }
                }
            });
        }

        public TextView getTextViewNome() {
            return textViewNome;
        }
    }
}
