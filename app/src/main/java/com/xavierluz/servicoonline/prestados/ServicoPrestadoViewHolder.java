package com.xavierluz.servicoonline.prestados;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xavierluz.servicoonline.R;

public  class ServicoPrestadoViewHolder extends RecyclerView.ViewHolder {
    public final TextView nomeServico;
    public final TextView descricaoServico;
    //public final TextView nomeCliente;
    public final TextView servicoValorPrestado;
    public final TextView servicoStatus;
    public final TextView dataServico;
    public final ImageButton imageButtonPrestadorDetalhe;
    public final TextView servicoPrestadoId;
    public ServicoPrestadoViewHolder(View view){
        super(view);
        this.nomeServico = (TextView) view.findViewById(R.id.textNomeServicoPrestado );
        this.descricaoServico = (TextView) view.findViewById(R.id.textDataCadastroServicoPrestado);
        //this.nomeCliente = (TextView) view.findViewById(R.id.textStatusPrestado );
        this.servicoValorPrestado = (TextView) view.findViewById(R.id.textValorServicoPrestado);
        this.servicoStatus = (TextView) view.findViewById(R.id.textStatusPrestado);
        this.dataServico = (TextView) view.findViewById(R.id.textDataCadastroServicoPrestado);
        this.imageButtonPrestadorDetalhe = (ImageButton) view.findViewById(R.id.imgButtonDetalhesServicoPrestado);
        this.servicoPrestadoId = (TextView) view.findViewById(R.id.textViewServicoPrestadoId);
    }
}
