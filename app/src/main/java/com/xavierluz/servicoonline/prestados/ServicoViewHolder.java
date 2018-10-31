package com.xavierluz.servicoonline.prestados;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xavierluz.servicoonline.R;

public class ServicoViewHolder extends RecyclerView.ViewHolder{
    public final TextView textDescricaoServicoPrestadoDetalhe;
    public final TextView textValorDoServicoPrestadoDetalhe;
    public final TextView textValorSerCobradoServicoPrestadoDetalhe;

    public ServicoViewHolder(View itemView) {
        super(itemView);


        this.textDescricaoServicoPrestadoDetalhe = (TextView) itemView.findViewById(R.id.textDescricaoServicoPrestadoDetalhe);
        this.textValorDoServicoPrestadoDetalhe = (TextView) itemView.findViewById(R.id.textValorDoServicoPrestadoDetalhe);
        this.textValorSerCobradoServicoPrestadoDetalhe = (TextView) itemView.findViewById(R.id.textValorSerCobradoServicoPrestadoDetalhe);
    }
}
