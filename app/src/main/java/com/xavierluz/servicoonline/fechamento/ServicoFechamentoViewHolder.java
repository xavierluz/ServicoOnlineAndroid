package com.xavierluz.servicoonline.fechamento;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xavierluz.servicoonline.R;

public class ServicoFechamentoViewHolder extends RecyclerView.ViewHolder  {
    public TextView textFechamentoId;
    public TextView textFechamentoServicoId;
    public TextView textDescricaoFechamento;
    public TextView textDataFechamento;
    public TextView textDataPorExtenso;

    public ServicoFechamentoViewHolder(View itemView) {
        super(itemView);
        this.textFechamentoId = (TextView) itemView.findViewById(R.id.textFechamentoId);
        this.textFechamentoServicoId = (TextView) itemView.findViewById(R.id.textFechamentoServicoId);
        this.textDescricaoFechamento = (TextView) itemView.findViewById(R.id.textDescricaoFechamento);
        this.textDescricaoFechamento = (TextView) itemView.findViewById(R.id.textDescricaoFechamento);
        this.textDataPorExtenso = (TextView) itemView.findViewById(R.id.textDataFechamento);
        this.textDataFechamento = (TextView) itemView.findViewById(R.id.textDataFechamento);
    }
}
