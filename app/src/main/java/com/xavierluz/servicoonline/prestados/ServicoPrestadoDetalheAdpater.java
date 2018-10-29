package com.xavierluz.servicoonline.prestados;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xavierluz.servicoonline.FechamentoAdapter;
import com.xavierluz.servicoonline.R;
import com.xavierluz.servicoonline.item.servico.ItemServico;

import java.util.List;

public class ServicoPrestadoDetalheAdpater  extends RecyclerView.Adapter{
    private List<ItemServico> itemServicos;
    private Context context;
    public ServicoPrestadoDetalheAdpater(List<ItemServico> itemServicos,Context context){
        super();
        this.context = context;
        this.itemServicos = itemServicos;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cards_servico_prestado_detalhe_layout, parent, false);
        ServicoItemPrestadoViewHolder servicoItemPrestadoViewHolder = new ServicoItemPrestadoViewHolder(view);

        return servicoItemPrestadoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ServicoItemPrestadoViewHolder servicoItemPrestadoViewHolder =(ServicoItemPrestadoViewHolder) holder;

        ItemServico itemServico = itemServicos.get(position);
        servicoItemPrestadoViewHolder.textNomeDoItem.setText(itemServico.getNomeItemServico());
        servicoItemPrestadoViewHolder.textDescricaoDoItem.setText(itemServico.getDescricaoItemServico());
        servicoItemPrestadoViewHolder.textPrecoDoItem.setText(FechamentoAdapter.formatarMoeda(itemServico.getPrecoItemServico()));
       // servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(itemServico.getQuantidadeItemServico());
      //  servicoItemPrestadoViewHolder.textValorCobradoDoItem.setText(FechamentoAdapter.formatarMoeda(itemServico.getValorCobradoDoItemServico()));
      //  servicoItemPrestadoViewHolder.textValorDoDescontoDoItem.setText(FechamentoAdapter.formatarMoeda(itemServico.getValorDoDescontoDoItemServico()));
      //  servicoItemPrestadoViewHolder.rdoDescontoPorcetagem.setChecked(itemServico.isDescontoPorcetagem());
      //  servicoItemPrestadoViewHolder.rdoDescontoValor.setChecked(!itemServico.isDescontoPorcetagem());
      //  Toast.makeText(this.context, "Selected Item: " + itemServico.getNomeItemServico(), Toast.LENGTH_SHORT).show();
        //servicoItemPrestadoViewHolder.textNomeDoItem.setText(servicoPrestado.getServico().getItemServicos().forEach(-> item););
        //servicoItemPrestadoViewHolder.nomeServico.setText(servicoPrestado.getServico().getNome());
        //servicoPrestadoViewHolder.nomeCliente.setText(servicoPrestado.getNomeCliente());
       // servicoItemPrestadoViewHolder.servicoValorPrestado.setText(FechamentoAdapter.formatarMoeda(servicoPrestado.getServicoValor()));
       // servicoItemPrestadoViewHolder.descricaoServico.setText(servicoPrestado.getServico().getNome());
       // servicoItemPrestadoViewHolder.servicoStatus.setText(servicoPrestado.getStatus());
       // servicoItemPrestadoViewHolder.dataServico.setText(servicoPrestado.getDataServicoCadastrado());
        //Toast.makeText(context, "Selected prestadores: " + servicoPrestado.getNomeServico(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return this.itemServicos.size();
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }
}
