package com.xavierluz.servicoonline.prestados;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xavierluz.servicoonline.R;

public class ServicoItemPrestadoViewHolder extends RecyclerView.ViewHolder {
    public final TextView textNomeDoItem;
    public final TextView textDescricaoDoItem;
    public final TextView textQuantidadeDoItem;
    public final TextView textPrecoDoItem;
    public final RadioButton rdoDescontoPorcetagem;
    public final RadioButton rdoDescontoValor;
    public final EditText textValorDoDescontoDoItem;
    public final TextView textValorCobradoDoItem;
    public ServicoItemPrestadoViewHolder(View itemView) {
        super(itemView);
        this.textNomeDoItem = (TextView) itemView.findViewById(R.id.textNomeItemServicoDetalhe);
        this.textDescricaoDoItem = (TextView) itemView.findViewById(R.id.textItemDescricaoServicoPrestadorDetalhe);
        this.textQuantidadeDoItem = (TextView) itemView.findViewById(R.id.textQuantidadeItemServico);
        this.textPrecoDoItem = (TextView) itemView.findViewById(R.id.textPrecoItemServicoPrestadorDetalhe);
        this.rdoDescontoPorcetagem = (RadioButton) itemView.findViewById(R.id.rdoDescontoPorcetagemDetalhe);
        this.rdoDescontoValor = (RadioButton) itemView.findViewById(R.id.rdoDescontoValorDetalhe);
        this.textValorDoDescontoDoItem = (EditText) itemView.findViewById(R.id.editValorDescontoDoItemDetalhe);
        this.textValorCobradoDoItem = (TextView) itemView.findViewById(R.id.textValorItemCobradoDetalhe);

    }
}