package com.xavierluz.servicoonline.item.servico;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.xavierluz.servicoonline.R;

public class ItemServicoViewHolder extends RecyclerView.ViewHolder  {
    public final TextView textNomeItemServico;
    public final TextView textDescricaoItemServico;
    public final TextView textPrecoItemServico;
    public final CheckBox chkItemServicoSelecionado;
    public int itemServicoId;
    public ItemServicoViewHolder(View itemView) {
        super(itemView);
        this.textNomeItemServico = (TextView) itemView.findViewById(R.id.textNomeItemServicoDaLista);
        this.textDescricaoItemServico = (TextView) itemView.findViewById(R.id.textItemDescricaoServicoLista);
        this.textPrecoItemServico = (TextView) itemView.findViewById(R.id.textPrecoItemServicoLista);
        this.chkItemServicoSelecionado = (CheckBox) itemView.findViewById(R.id.checkItemSevicoSelecionar);
        this.itemServicoId=0;
    }
}
