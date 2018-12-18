package com.xavierluz.servicoonline;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xavierluz.servicoonline.item.servico.ItemServico;
import com.xavierluz.servicoonline.prestados.ServicoItemPrestadoViewHolder;
import com.xavierluz.servicoonline.prestados.ServicoPrestadoServices;

import java.util.ArrayList;
import java.util.List;

public class DetalheServicoPrestadoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private android.support.v7.widget.RecyclerView recycleViewListaItemServico;
    private RecyclerView.LayoutManager layoutManager;
    private String servicoId;
    private String servicoPrestadoId;
    private TextView textValorSerCobradoServicoPrestadoDetalhe;
    private RecyclerView recycleViewPrestadoDetalhe;
    private TextView textValorDoServicoPrestadoDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_servico_prestado);
        this.recycleViewPrestadoDetalhe = (RecyclerView) findViewById(R.id.recycleViewItensServicosPrestados);
        this.recycleViewPrestadoDetalhe.setHasFixedSize(true);


        toolbar = (Toolbar) findViewById(R.id.toolbarServicoPrestadoDetalhe);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Display icon in the toolbar
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //get the intent in the target activity
        Intent intent = getIntent();
        //get the attached bundle from the intent
        Bundle extras = intent.getExtras();
        //Extracting the stored data from the bundle
        this.servicoPrestadoId = extras.getString("servicoPrestadoId");
       // Toast.makeText(this, "Selected Item: " + servicoPrestadoId, Toast.LENGTH_SHORT).show();
        View view =(View) findViewById(R.id.activity_item_servico_prestado_detalhe);

        final ServicoPrestadoServices servicoPrestadoServices = ServicoPrestadoServices.createRecycleViewServicoPrestado(DetalheServicoPrestadoActivity.this, this.recycleViewPrestadoDetalhe);
        servicoPrestadoServices.setServicoPrestado(view, this.servicoPrestadoId);
        servicoPrestadoServices.setServicosPrestadoDetalhe(this.servicoPrestadoId);

        this.recycleViewPrestadoDetalhe.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                calcularTotalDesconto(holder);
            }
        });
        textValorDoServicoPrestadoDetalhe = (TextView) findViewById(R.id.textValorDoServicoPrestadoDetalhe);
        ImageButton imgButtonSalvarServicoPrestado = (ImageButton) findViewById(R.id.imgButtonSalvarServicoPrestado);
        imgButtonSalvarServicoPrestado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicoPrestadoServices.Atualizar(servicoPrestadoId,FechamentoAdapter.formatoDecimalSemTipoMoeda(textValorDoServicoPrestadoDetalhe.getText().toString()),getItemServicos());
                Intent intent = new Intent(DetalheServicoPrestadoActivity.this, PagamentoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("servicoPrestadoId",servicoPrestadoId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }
    private void calcularTotalDesconto(RecyclerView.ViewHolder holder){
        Double desconto= 0.0;
        Double _valorDoServico = 0.0;


            RecyclerView.ViewHolder viewHolder =(ServicoItemPrestadoViewHolder) holder;
            if (viewHolder != null) {
                final ServicoItemPrestadoViewHolder itemServicoViewHolder = (ServicoItemPrestadoViewHolder) viewHolder;
                //desconto += FechamentoAdapter.formatoDecimalSemTipoMoeda(itemServicoViewHolder.textDesconto.getText().toString().replace("Valor desconto","0"));

            }

        this.textValorSerCobradoServicoPrestadoDetalhe.setText(FechamentoAdapter.formatarMoeda(_valorDoServico));
    }
    private List<ItemServico> getItemServicos(){
        List<ItemServico> itemServicos = new ArrayList<ItemServico>();

        for (int i =  recycleViewPrestadoDetalhe.getChildCount() - 1; i >= 0; i--) {
            final View view = recycleViewPrestadoDetalhe.getChildAt(i);
            RecyclerView.ViewHolder viewHolder = recycleViewPrestadoDetalhe.getChildViewHolder(view);
            if (viewHolder != null && viewHolder instanceof ServicoItemPrestadoViewHolder) {
                ItemServico itemServico = new ItemServico();

                final ServicoItemPrestadoViewHolder itemServicoViewHolder = (ServicoItemPrestadoViewHolder) viewHolder;

                itemServico.setQuantidadeItemServico( Integer.parseInt(itemServicoViewHolder.textQuantidadeDoItem.getText().toString()));
                itemServico.setAtivo(true);
                itemServico.setDesconto(FechamentoAdapter.formatoDecimalSemTipoMoeda(itemServicoViewHolder.textValorCobradoDoServico.getText().toString()));
                itemServico.setDescricaoItemServico(itemServicoViewHolder.textDescricaoDoItem.getText().toString());
                itemServico.setId(itemServicoViewHolder.textViewItemServicoId.getText().toString());
                itemServico.setPrecoItemServico(FechamentoAdapter.formatoDecimalSemTipoMoeda(itemServicoViewHolder.textPrecoDoItem.getText().toString()));
                itemServico.setValorTotalDoItem(FechamentoAdapter.formatoDecimalSemTipoMoeda(itemServicoViewHolder.textValorCobradoDoServico.getText().toString()));
                itemServico.setNomeItemServico(itemServicoViewHolder.textNomeDoItem.getText().toString());
                itemServicos.add(itemServico);
                itemServico = null;
            }
        }

        return itemServicos;
    }
}
