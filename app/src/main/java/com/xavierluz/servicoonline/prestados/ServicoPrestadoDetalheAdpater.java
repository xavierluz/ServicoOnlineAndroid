package com.xavierluz.servicoonline.prestados;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xavierluz.servicoonline.FechamentoAdapter;
import com.xavierluz.servicoonline.R;
import com.xavierluz.servicoonline.item.servico.ItemServico;

import java.util.List;

public class ServicoPrestadoDetalheAdpater  extends RecyclerView.Adapter{
    private List<ItemServico> itemServicos;
    private Context context;
    //atributo da classe.
    private AlertDialog alertDialog;
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
        final ServicoItemPrestadoViewHolder servicoItemPrestadoViewHolder =(ServicoItemPrestadoViewHolder) holder;

        ItemServico itemServico = itemServicos.get(position);
        servicoItemPrestadoViewHolder.textNomeDoItem.setText(itemServico.getNomeItemServico());
        servicoItemPrestadoViewHolder.textDescricaoDoItem.setText(itemServico.getDescricaoItemServico());
        servicoItemPrestadoViewHolder.textPrecoDoItem.setText(FechamentoAdapter.formatarMoeda(itemServico.getPrecoItemServico()));
        servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(itemServico.getQuantidadeItemServico().toString());
        servicoItemPrestadoViewHolder.textValorCobradoDoItem.setText(FechamentoAdapter.formatarMoeda(itemServico.getValorCobradoDoItemServico()));
        servicoItemPrestadoViewHolder.textValorDoDescontoDoItem.setText(FechamentoAdapter.formatarMoeda(itemServico.getValorDoDescontoDoItemServico()));
        servicoItemPrestadoViewHolder.textValorItemCobradoDetalhe.setText(FechamentoAdapter.formatarMoeda(itemServico.getValorCobradoDoItemServico()));
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
        servicoItemPrestadoViewHolder.imgButtonAumentarQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(seAumentartQuantidade(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString()).toString());
            }
        });
        servicoItemPrestadoViewHolder.imgButtonDiminuirQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(seDiminuirQuantidade(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString()).toString());
            }
        });
        servicoItemPrestadoViewHolder.imgButtonDeletarItemServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater =  LayoutInflater.from(context);
                //inflamos o layout alerta.xml na view
                View view = layoutInflater.inflate(R.layout.dialogo_confirmar, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Titulo");
                TextView textDialgoTitulo =(TextView) view.findViewById(R.id.textDialogoTitulo);
                TextView textDialogoMensagem = (TextView) view.findViewById(R.id.textDialogoMensagem);
                textDialgoTitulo.setText("Excluir i item do serviço");
                textDialogoMensagem.setText("Tem certeza? O item não poderá ser recuperado");
                builder.setView(view);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
    private Integer seAumentartQuantidade(String quantidade){
        Integer _quantidade;
        if(quantidade == ""){
            _quantidade = 1;
        }else{
            _quantidade = Integer.parseInt(quantidade);
        }
        //Toast.makeText(this.context, "Quantidade :  " + _quantidade.toString(), Toast.LENGTH_SHORT).show();
        return _quantidade +=1;
    }
    private Integer seDiminuirQuantidade(String quantidade){
        Integer _quantidade = 0;
        if(quantidade == ""){
            _quantidade = 1;
        }else{
            _quantidade = Integer.parseInt(quantidade);
        }
        //Toast.makeText(this.context, "Quantidade :  " + _quantidade.toString(), Toast.LENGTH_SHORT).show();
        Integer retorno = _quantidade -=1;
        if(retorno  < 1){
            Toast.makeText(this.context, "Quantidade não poderá ser menor que 1:  " + retorno.toString(), Toast.LENGTH_SHORT).show();
            return 1;
        }

        return retorno;
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

    private void dialogoConfirmarExcluir(){
        LayoutInflater layoutInflater =  LayoutInflater.from(context);
        //inflamos o layout alerta.xml na view
        View view = layoutInflater.inflate(R.layout.dialogo_confirmar, null);
//definimos para o botão do layout um clickListener
        view.findViewById(R.id.imgButtonDeletarItemServico).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //exibe um Toast informativo.
                Toast.makeText(context, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
                //desfaz o alerta.
                alertDialog.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Titulo");
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
    }
}
