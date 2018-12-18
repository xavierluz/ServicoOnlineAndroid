package com.xavierluz.servicoonline.prestados;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xavierluz.servicoonline.R;

public class ServicoItemPrestadoViewHolder extends RecyclerView.ViewHolder {
    public final TextView textViewPrestadoServicoId;
    public final TextView textViewServicoPrestadoId;
    public final TextView textViewItemServicoId;
    public final TextView textNomeDoItem;
    public final TextView textDescricaoDoItem;
    public final TextView textQuantidadeDoItem;
    public final TextView textPrecoDoItem;
    public final TextView textValorCobradoDoServico;
    public final ImageButton imgButtonAumentarQuantidade;
    public final ImageButton imgButtonDiminuirQuantidade;
    public final ImageButton imgButtonDeletarItemServico;
    public final ImageButton imgButtonDetalhesServicoPrestado;
    public final ImageButton imgButtonCancelarItem;

    public ServicoItemPrestadoViewHolder(View itemView) {
        super(itemView);
        this.textViewPrestadoServicoId = (TextView) itemView.findViewById(R.id.textViewPrestadoServicoId);
        this.textViewServicoPrestadoId = (TextView) itemView.findViewById(R.id.textViewServicoPrestadoId);
        this.textViewItemServicoId = (TextView) itemView.findViewById(R.id.textViewItemServicoId);
        this.textNomeDoItem = (TextView) itemView.findViewById(R.id.textNomeItemServicoDetalhe);
        this.textDescricaoDoItem = (TextView) itemView.findViewById(R.id.textItemDescricaoServicoPrestadorDetalhe);
        this.textQuantidadeDoItem = (TextView) itemView.findViewById(R.id.textQuantidadeItemServico);
        this.textPrecoDoItem = (TextView) itemView.findViewById(R.id.textPrecoItemServicoPrestadorDetalhe);
        this.imgButtonAumentarQuantidade = (ImageButton) itemView.findViewById(R.id.imgButtonAdionarItemDetalhe);
        this.imgButtonDiminuirQuantidade = (ImageButton) itemView.findViewById(R.id.imgButtonDiminuirItemDetalhe);
        this.imgButtonDeletarItemServico = (ImageButton) itemView.findViewById(R.id.imgButtonDeletarItemServico);
        this.imgButtonDetalhesServicoPrestado = (ImageButton) itemView.findViewById(R.id.imgButtonDetalhesServicoPrestado);
        this.textValorCobradoDoServico =(TextView) itemView.findViewById(R.id.textValorCobradoDoServico);
        this.imgButtonCancelarItem = (ImageButton) itemView.findViewById(R.id.imgButtonCancelarItem);
    }
}
