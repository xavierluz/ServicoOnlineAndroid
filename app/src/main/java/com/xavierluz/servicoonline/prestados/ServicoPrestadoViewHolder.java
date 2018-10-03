package com.xavierluz.servicoonline.prestados;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xavierluz.servicoonline.R;

import java.util.Currency;

public  class ServicoPrestadoViewHolder extends RecyclerView.ViewHolder {
    public final TextView nomeServico;
    public final TextView descricaoServico;
    public final TextView nomeCliente;
    public final TextView servicoValorCobrado;
    public final TextView servicoStatus;
    public ServicoPrestadoViewHolder(View view){
        super(view);
        this.nomeServico = (TextView) view.findViewById(R.id.textServicoName );
        this.descricaoServico = (TextView) view.findViewById(R.id.textDescricao );
        this.nomeCliente = (TextView) view.findViewById(R.id.textCliente );
        this.servicoValorCobrado = (TextView) view.findViewById(R.id.textValor );
        this.servicoStatus = (TextView) view.findViewById(R.id.textStatus);
    }
}
