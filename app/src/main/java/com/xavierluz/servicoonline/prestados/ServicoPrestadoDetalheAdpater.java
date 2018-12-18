package com.xavierluz.servicoonline.prestados;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.xavierluz.servicoonline.FechamentoAdapter;
import com.xavierluz.servicoonline.R;

import com.xavierluz.servicoonline.item.servico.ItemServico;
import com.xavierluz.servicoonline.item.servico.Servico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ServicoPrestadoDetalheAdpater  extends RecyclerView.Adapter{
    private List<ItemServico> itemServicos;
    private Context context;
    private FirebaseDatabase database;
    private DatabaseReference refServicos;


    //atributo da classe.
    private AlertDialog alertDialog;
    private View viewServicoPrestado;
    private TextView textValorDoServicoPrestadoDetalhe;
    private Double valorTotalDoservico = 0.0;
    private RecyclerView recyclerView;
    private Double _valorDoServico = 0.0;
    private String servicoPrestadoId;

    public ServicoPrestadoDetalheAdpater(List<ItemServico> itemServicos,Context context){
        super();
        this.context = context;
        this.itemServicos = itemServicos;
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicosPrestado");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cards_servico_prestado_detalhe_layout, parent, false);
        ServicoItemPrestadoViewHolder servicoItemPrestadoViewHolder = new ServicoItemPrestadoViewHolder(view);
        View viewListaItensServico = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        viewServicoPrestado = viewListaItensServico.findViewById(R.id.activity_item_servico_prestado_detalhe);
        textValorDoServicoPrestadoDetalhe =(TextView) viewServicoPrestado.findViewById(R.id.textValorDoServicoPrestadoDetalhe);
        recyclerView = (RecyclerView) viewListaItensServico.findViewById(R.id.recycleViewItensServicosPrestados);
        return servicoItemPrestadoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ServicoItemPrestadoViewHolder servicoItemPrestadoViewHolder =(ServicoItemPrestadoViewHolder) holder;

        ItemServico itemServico = itemServicos.get(position);
        servicoItemPrestadoViewHolder.textViewPrestadoServicoId.setText(itemServico.getServicoId());
        servicoItemPrestadoViewHolder.textViewServicoPrestadoId.setText(itemServico.getServicoPrestadoId());
        servicoItemPrestadoViewHolder.textViewItemServicoId.setText(itemServico.getId());
        servicoItemPrestadoViewHolder.textNomeDoItem.setText(itemServico.getNomeItemServico());
        servicoItemPrestadoViewHolder.textDescricaoDoItem.setText(itemServico.getDescricaoItemServico());
        servicoItemPrestadoViewHolder.textPrecoDoItem.setText(FechamentoAdapter.formatarMoeda(itemServico.getPrecoItemServico()));
        servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(itemServico.getQuantidadeItemServico().toString());
        Double valorCobradoDoItem = calcularValorDoServico(itemServico.getQuantidadeItemServico(),itemServico.getPrecoItemServico());

        servicoItemPrestadoViewHolder.textValorCobradoDoServico.setText(FechamentoAdapter.formatarMoeda(valorCobradoDoItem));

        valorTotalDoservico += valorCobradoDoItem;
        textValorDoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(valorTotalDoservico));


        //Toast.makeText(this.context, "Selected Item: " + servicoItemPrestadoViewHolder.textDesconto.getText().toString(), Toast.LENGTH_SHORT).show();

        servicoItemPrestadoViewHolder.imgButtonCancelarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoConfirmarCancelar();
            }
        });


        servicoItemPrestadoViewHolder.imgButtonAumentarQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantidade = Integer.parseInt(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString());
                Double valorDoItem =FechamentoAdapter.formatoDecimalSemTipoMoeda (servicoItemPrestadoViewHolder.textPrecoDoItem.getText().toString());

                valorTotalDoservico =  valorTotalDoservico - (valorDoItem * quantidade);
                quantidade +=1;

                Double valorDoServico = calcularValorDoServico(quantidade,valorDoItem);
                servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(quantidade.toString());

                servicoItemPrestadoViewHolder.textValorCobradoDoServico.setText(FechamentoAdapter.formatarMoeda(valorDoServico));

                valorTotalDoservico += valorDoServico;
                textValorDoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(valorTotalDoservico));

                servicoPrestadoId = servicoItemPrestadoViewHolder.textViewServicoPrestadoId.getText().toString();

                DatabaseReference refServicosPrestados = database.getReference("servicosPrestado");
                refServicosPrestados.child(servicoPrestadoId).child("servicoValor").setValue(valorTotalDoservico);
                refServicosPrestados.child(servicoPrestadoId)
                        .child("servico")
                        //.child(servicoItemPrestadoViewHolder.textViewPrestadoServicoId.getText().toString())
                        .child("itemServicos")
                        .child("0")
                        .child("valorCobradoDoItemServico").setValue(valorDoServico);

            }
        });
        servicoItemPrestadoViewHolder.imgButtonDiminuirQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer quantidade = Integer.parseInt(servicoItemPrestadoViewHolder.textQuantidadeDoItem.getText().toString());
                Double valorDoItem =FechamentoAdapter.formatoDecimalSemTipoMoeda (servicoItemPrestadoViewHolder.textPrecoDoItem.getText().toString());

                valorTotalDoservico =  valorTotalDoservico - (valorDoItem * quantidade);
                quantidade -=1;
                if(quantidade == 0)
                    quantidade = 1;

                Double valorDoServico = calcularValorDoServico(quantidade,valorDoItem);
                servicoItemPrestadoViewHolder.textQuantidadeDoItem.setText(quantidade.toString());

                servicoItemPrestadoViewHolder.textValorCobradoDoServico.setText(FechamentoAdapter.formatarMoeda(valorDoServico));

                valorTotalDoservico += valorDoServico;
                textValorDoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(valorTotalDoservico));

            }
        });

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

    private void dialogoConfirmarCancelar(){
        LayoutInflater layoutInflater =  LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialogo_confirmar, null);

        view.findViewById(R.id.imgButtonDialogoConfirmar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Toast.makeText(context, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }

        });
        view.findViewById(R.id.imgButtonDialogoCancelar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Toast.makeText(context, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }

        });
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Titulo");
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();

    }

    private Double calcularValorDoServico(Integer quantidade, Double valor){
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
    private boolean verificarDecimal( String texto ) {
        if (Pattern.matches ("(\\d)*,\\d{2}+",texto)) {
            return true;
        }
        return false;
    }
    private void calcularTotalDesconto(ServicoItemPrestadoViewHolder holder){
        Double desconto= 0.0;

        RecyclerView.ViewHolder viewHolder = holder;
        if (viewHolder != null) {
            final ServicoItemPrestadoViewHolder itemServicoViewHolder = (ServicoItemPrestadoViewHolder) viewHolder;
            //desconto += FechamentoAdapter.formatoDecimalSemTipoMoeda(itemServicoViewHolder.textDesconto.getText().toString().replace("Valor desconto","0"));
            //Toast.makeText(context, itemServicoViewHolder.textValorTotalCobrado.getText().toString(), Toast.LENGTH_SHORT).show();
        }


    }
    private void calcularTotalDescontos(){
        Double desconto= 0.0;

        Toast.makeText(context, Integer.toString(recyclerView.getAdapter().getItemCount()), Toast.LENGTH_SHORT).show();


        for (int i =  recyclerView.getChildCount() - 1; i >= 0; i--) {
            final View view = recyclerView.getChildAt(i);
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder != null && viewHolder instanceof ServicoItemPrestadoViewHolder) {
                final ServicoItemPrestadoViewHolder itemServicoViewHolder = (ServicoItemPrestadoViewHolder) viewHolder;
                //desconto += FechamentoAdapter.formatoDecimalSemTipoMoeda(itemServicoViewHolder.textDesconto.getText().toString().replace("Valor desconto","0"));
               // Toast.makeText(context, itemServicoViewHolder.textValorTotalCobrado.getText().toString(), Toast.LENGTH_SHORT).show();
                //_valorDoServico +=  FechamentoAdapter.formatoDecimalSemTipoMoeda(itemServicoViewHolder.textValorTotalCobrado.getText().toString());
            }
        }

    }
}
