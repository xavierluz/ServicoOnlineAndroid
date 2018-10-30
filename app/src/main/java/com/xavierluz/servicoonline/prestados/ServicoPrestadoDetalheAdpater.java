package com.xavierluz.servicoonline.prestados;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
        servicoItemPrestadoViewHolder.textValorTotalCobrado.setText(FechamentoAdapter.formatarMoeda(itemServico.getValorTotalDoItem()));
        servicoItemPrestadoViewHolder.textValorDoDesconto.setText(FechamentoAdapter.formatarMoeda(itemServico.getValorDoDesconto()));
        servicoItemPrestadoViewHolder.textValorTotalDoItem.setText(FechamentoAdapter.formatarMoeda(itemServico.getValorDoItemServico()));
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
                Integer _quantidade = Integer.parseInt(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString());
                Double _valor = FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textPrecoDoItem.getText().toString());
                servicoItemPrestadoViewHolder.textValorDoItem.setText(FechamentoAdapter.formatarMoeda(calcularValorDoItem(_quantidade,_valor)));
            }
        });
        servicoItemPrestadoViewHolder.imgButtonDiminuirQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(seDiminuirQuantidade(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString()).toString());
                Integer _quantidade = Integer.parseInt(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString());
                Double _valor = FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textPrecoDoItem.getText().toString());
                servicoItemPrestadoViewHolder.textValorDoItem.setText(FechamentoAdapter.formatarMoeda(calcularValorDoItem(_quantidade,_valor)));
            }
        });

        servicoItemPrestadoViewHolder.textValorDoDesconto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Double valorDesconto =  FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorDoDesconto.getText().toString());
                Double valorTotalItemServico =  FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorDoItem.getText().toString());
                String valorCobrado ="";
                if(servicoItemPrestadoViewHolder.rdoDescontoPorcetagem.isChecked()){
                    valorCobrado = FechamentoAdapter.formatarMoeda(calcularDescontoPorPorcetagem(valorDesconto,valorTotalItemServico));
                }else{
                    valorCobrado = FechamentoAdapter.formatarMoeda(calcularDescontoPorValor(valorDesconto,valorTotalItemServico));
                }

                servicoItemPrestadoViewHolder.textValorTotalDoItem.setText(valorCobrado);
            }
        });
        servicoItemPrestadoViewHolder.textValorDoDesconto.addTextChangedListener(new TextWatcher() {
            Double valorDesconto =  FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorDoDesconto.getText().toString());
            Double valorTotalItemServico =  FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorDoItem.getText().toString());
            String valorCobrado ="";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              //  Toast.makeText(context, "onTextChanged" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(servicoItemPrestadoViewHolder.rdoDescontoPorcetagem.isChecked()){
                    valorCobrado = FechamentoAdapter.formatarMoeda(calcularDescontoPorPorcetagem(valorDesconto,valorTotalItemServico));
                }else{
                    valorCobrado = FechamentoAdapter.formatarMoeda(calcularDescontoPorValor(valorDesconto,valorTotalItemServico));
                    Toast.makeText(context, "valorDesconto" + valorCobrado.toString() , Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "valorCobrado" + valorCobrado.toString() , Toast.LENGTH_SHORT).show();
                }

                servicoItemPrestadoViewHolder.textValorTotalCobrado.setText(valorCobrado);
            }
        });
        servicoItemPrestadoViewHolder.imgButtonDeletarItemServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater =  LayoutInflater.from(context);
                //inflamos o layout alerta.xml na view
                View view = layoutInflater.inflate(R.layout.dialogo_confirmar, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Excluir i item do serviço");
                TextView textDialogoMensagem = (TextView) view.findViewById(R.id.textDialogoMensagem);
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

    private Double calcularValorDoItem(Integer quantidade, Double valor){
        return quantidade * valor;
    }
    private Double calcularDescontoPorValor(Double valorDesconto, Double valorTotalDoItem) {
        if (valorDesconto > valorTotalDoItem) {
            return valorTotalDoItem - valorDesconto;
        }
        return valorTotalDoItem;
    }
    private Double calcularDescontoPorPorcetagem(Double valorPorcetagem, Double valorTotalDoItem) {
        Double calcularPorcetagem =calcularPorcetagem(valorPorcetagem,valorTotalDoItem);
        if(calcularPorcetagem > valorTotalDoItem){
            return valorTotalDoItem - calcularPorcetagem;
        }
        return valorTotalDoItem;
    }

    private Double calcularPorcetagem(Double porcetagem, Double valor){
        return (valor * porcetagem) / 100;
    }
}
