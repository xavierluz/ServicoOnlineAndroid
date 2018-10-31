package com.xavierluz.servicoonline.prestados;

import android.app.Activity;
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
    private View viewServicoPrestado;
    private TextView textValorDoServicoPrestadoDetalhe;
    private Double valorTotalDoservico = 0.0;
    private TextView textValorSerCobradoServicoPrestadoDetalhe ;
    private Double valorDoItemComDesconto = 0.0;
    private Double valorTotalDoDesconto = 0.0;
    private Double Desconto = 0.0;
    private Integer contadorDiminuirQuantidade = 0;
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
        View viewListaItensServico = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        viewServicoPrestado = viewListaItensServico.findViewById(R.id.activity_item_servico_prestado_detalhe);
        textValorDoServicoPrestadoDetalhe =(TextView) viewServicoPrestado.findViewById(R.id.textValorDoServicoPrestadoDetalhe);
        textValorSerCobradoServicoPrestadoDetalhe = (TextView) viewServicoPrestado.findViewById(R.id.textValorSerCobradoServicoPrestadoDetalhe);
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

        if(itemServico.getValorDoDesconto() < 1){
            servicoItemPrestadoViewHolder.textValorTotalCobrado.setText(FechamentoAdapter.formatarMoeda(calcularValorDoItem(itemServico.getQuantidadeItemServico(), itemServico.getPrecoItemServico())));
        }else{
            servicoItemPrestadoViewHolder.textValorTotalCobrado.setText(FechamentoAdapter.formatarMoeda(itemServico.getValorTotalDoItem()));
        }

        servicoItemPrestadoViewHolder.textValorDoDesconto.setText(Double.toString(itemServico.getValorDoDesconto()));
        servicoItemPrestadoViewHolder.textValorTotalDoItem.setText(FechamentoAdapter.formatarMoeda(calcularValorDoItem(itemServico.getQuantidadeItemServico(), itemServico.getPrecoItemServico())));
        valorTotalDoservico += FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorTotalDoItem.getText().toString());
        textValorDoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(valorTotalDoservico));
        valorTotalDoDesconto += FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorTotalCobrado.getText().toString());
        if(textValorSerCobradoServicoPrestadoDetalhe.getText().length() == 0)
            textValorSerCobradoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(valorTotalDoservico - Desconto));
        else {
            //Toast.makeText(this.context, "Desconto " + Desconto.toString(), Toast.LENGTH_SHORT).show();

            //Toast.makeText(this.context, "valorTotalDoDesconto " + valorTotalDoDesconto.toString(), Toast.LENGTH_SHORT).show();
            textValorSerCobradoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(valorTotalDoDesconto));
        }


        servicoItemPrestadoViewHolder.rdoDescontoPorcetagem.setChecked(itemServico.isDescontoPorcetagem());
        servicoItemPrestadoViewHolder.rdoDescontoValor.setChecked(!itemServico.isDescontoPorcetagem());
        servicoItemPrestadoViewHolder.textDesconto.setText(FechamentoAdapter.formatarMoeda(itemServico.getDesconto()));
        //  Toast.makeText(this.context, "Selected Item: " + itemServico.getNomeItemServico(), Toast.LENGTH_SHORT).show();
        //servicoItemPrestadoViewHolder.textNomeDoItem.setText(servicoPrestado.getServico().getItemServicos().forEach(-> item););
        //servicoItemPrestadoViewHolder.nomeServico.setText(servicoPrestado.getServico().getNome());
        //servicoPrestadoViewHolder.nomeCliente.setText(servicoPrestado.getNomeCliente());
       // servicoItemPrestadoViewHolder.servicoValorPrestado.setText(FechamentoAdapter.formatarMoeda(servicoPrestado.getServicoValor()));
       // servicoItemPrestadoViewHolder.descricaoServico.setText(servicoPrestado.getServico().getNome());
       // servicoItemPrestadoViewHolder.servicoStatus.setText(servicoPrestado.getStatus());
       // servicoItemPrestadoViewHolder.dataServico.setText(servicoPrestado.getDataServicoCadastrado());
        //Toast.makeText(context, "Selected prestadores: " + servicoPrestado.getNomeServico(), Toast.LENGTH_SHORT).show();
        servicoItemPrestadoViewHolder.imgButtonDetalhesServicoPrestado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valorCobradoDoServico = FechamentoAdapter.formatoDecimalSemTipoMoeda(textValorSerCobradoServicoPrestadoDetalhe.getText().toString())- FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textDesconto.getText().toString());


                textValorSerCobradoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(valorCobradoDoServico));

            }
        });
        servicoItemPrestadoViewHolder.imgButtonAumentarQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(seAumentartQuantidade(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString()).toString());
                Integer _quantidade = Integer.parseInt(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString());
                Double _valor = FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textPrecoDoItem.getText().toString());
                Double valorCalculado = calcularValorDoItem(_quantidade,_valor);
                servicoItemPrestadoViewHolder.textValorDoItem.setText(FechamentoAdapter.formatarMoeda(valorCalculado));
                Double valorDoServicoPrestadoDetalhe =  FechamentoAdapter.formatoDecimalSemTipoMoeda(textValorDoServicoPrestadoDetalhe.getText().toString());
                Double valorSerCobradoServicoPrestadoDetalhe =  FechamentoAdapter.formatoDecimalSemTipoMoeda(textValorSerCobradoServicoPrestadoDetalhe.getText().toString());

                Double _valorDesconto =  FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorDoDesconto.getText().toString());
                textValorDoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(valorDoServicoPrestadoDetalhe +  _valor));
               // Toast.makeText(context, "_valorDesconto: " + _valorDesconto.toString(), Toast.LENGTH_SHORT).show();
                Desconto = (valorDoServicoPrestadoDetalhe +  _valor) - _valorDesconto;
                textValorSerCobradoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(Desconto));//- Desconto));
                contadorDiminuirQuantidade +=1;

            }
        });
        servicoItemPrestadoViewHolder.imgButtonDiminuirQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(seDiminuirQuantidade(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString()).toString());
                Integer _quantidade = Integer.parseInt(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString());
                if(contadorDiminuirQuantidade > 0) {
                    Double _valor = FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textPrecoDoItem.getText().toString());
                    Double valorCalculado = calcularValorDoItem(_quantidade, _valor);
                    servicoItemPrestadoViewHolder.textValorDoItem.setText(FechamentoAdapter.formatarMoeda(valorCalculado));
                    Double valorDoServicoPrestadoDetalhe = FechamentoAdapter.formatoDecimalSemTipoMoeda(textValorDoServicoPrestadoDetalhe.getText().toString());
                    Double valorSerCobradoServicoPrestadoDetalhe =  FechamentoAdapter.formatoDecimalSemTipoMoeda(textValorSerCobradoServicoPrestadoDetalhe.getText().toString());
                    Double _valorDesconto =  FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorDoDesconto.getText().toString());
                    textValorDoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(valorDoServicoPrestadoDetalhe - _valor));
                    Desconto = (valorDoServicoPrestadoDetalhe - _valor) - _valorDesconto;
                    textValorSerCobradoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(Desconto));
                }
                contadorDiminuirQuantidade -=1;
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
        servicoItemPrestadoViewHolder.textValorDoDesconto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
               //if (!hasFocus)
                    //Toast.makeText(context, "onFocusChange" , Toast.LENGTH_SHORT).show();
            }
        });
        servicoItemPrestadoViewHolder.textValorDoDesconto.addTextChangedListener(new TextWatcher() {
            //Double valorDesconto =  FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorDoDesconto.getText().toString());
            Double valorTotalItemServico =  FechamentoAdapter.formatoDecimalSemTipoMoeda(servicoItemPrestadoViewHolder.textValorDoItem.getText().toString());
            String valorCobrado ="";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable value) {
               if(value.length() > 0) {
                   //Toast.makeText(context, "subSequence" +value.subSequence(0,1).toString() , Toast.LENGTH_SHORT).show();
                   Double _Desconto= Double.parseDouble(value.toString().replace(",","."));

                   if (servicoItemPrestadoViewHolder.rdoDescontoPorcetagem.isChecked()) {
                       valorDoItemComDesconto = calcularDescontoPorPorcetagem(_Desconto, valorTotalItemServico);
                       valorCobrado = FechamentoAdapter.formatarMoeda(valorDoItemComDesconto);
                   } else {
                       valorDoItemComDesconto = calcularDescontoPorValor(_Desconto, valorTotalItemServico);
                       valorCobrado = FechamentoAdapter.formatarMoeda(valorDoItemComDesconto);
                       //Toast.makeText(context, "valorDesconto" + valorDesconto.toString() , Toast.LENGTH_SHORT).show();
                       //Toast.makeText(context, "valorCobrado" + valorCobrado.toString() , Toast.LENGTH_SHORT).show();
                       // Toast.makeText(context, "valorDesconto" + valorDesconto.toString() , Toast.LENGTH_SHORT).show();
                       //Toast.makeText(context, "valorTotalItemServico" + valorTotalItemServico.toString() , Toast.LENGTH_SHORT).show();
                   }


                   servicoItemPrestadoViewHolder.textValorTotalCobrado.setText(valorCobrado);
                   servicoItemPrestadoViewHolder.textDesconto.setText(FechamentoAdapter.formatarMoeda(_Desconto));

                   Desconto += _Desconto;
               }
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
        Toast.makeText(context, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
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
        if (valorTotalDoItem > valorDesconto) {
            return valorTotalDoItem - valorDesconto;
        }
        return valorTotalDoItem;
    }
    private Double calcularDescontoPorPorcetagem(Double valorPorcetagem, Double valorTotalDoItem) {
        Double calcularPorcetagem =calcularPorcetagem(valorPorcetagem,valorTotalDoItem);
        if(valorTotalDoItem > valorPorcetagem){
            return valorTotalDoItem - calcularPorcetagem;
        }
        return valorTotalDoItem;
    }

    private Double calcularPorcetagem(Double porcetagem, Double valor){
        return (valor * porcetagem) / 100;
    }
}
