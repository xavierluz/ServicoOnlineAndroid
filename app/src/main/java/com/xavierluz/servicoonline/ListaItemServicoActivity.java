package com.xavierluz.servicoonline;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xavierluz.servicoonline.fechamento.ServicoFechamento;
import com.xavierluz.servicoonline.item.servico.ItemServico;
import com.xavierluz.servicoonline.item.servico.ItemServicoAdapter;
import com.xavierluz.servicoonline.item.servico.ItemServicoViewHolder;
import com.xavierluz.servicoonline.prestados.ServicoPrestado;
import com.xavierluz.servicoonline.prestados.ServicoPrestadoServices;
import com.xavierluz.servicoonline.servico.ServicoItem;
import com.xavierluz.servicoonline.servico.ServicoItemServices;
import com.xavierluz.servicoonline.servico.ServicoServices;

import java.util.ArrayList;
import java.util.List;

public class ListaItemServicoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recycleViewListaItemServico;
    private RecyclerView.LayoutManager layoutManager;
    private String servicoId;
    private ServicoItemServices servicoItemServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_item_servico);
        toolbar = (Toolbar) findViewById(R.id.toolbarListaItemServico);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Display icon in the toolbar
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //get the intent in the target activity
        Intent intent = getIntent();
        //get the attached bundle from the intent
        Bundle extras = intent.getExtras();
        //Extracting the stored data from the bundle
        this.servicoId = extras.getString("ServicoId");
        //Toast.makeText(this, "Selected Item: " + servicoId, Toast.LENGTH_SHORT).show();

        final TextView textViewValorTotal = (TextView) findViewById(R.id.textViewValorTotalServicoPrestado);
        textViewValorTotal.setText("0.00");
        this.recycleViewListaItemServico = (RecyclerView) findViewById(R.id.recycleViewListaItemServico);
        this.recycleViewListaItemServico.setHasFixedSize(true);

        servicoItemServices = ServicoItemServices.createRecycleViewServicoItem(this,this.recycleViewListaItemServico,this.servicoId);
        servicoItemServices.setServicos();
        /*
        layoutManager = new LinearLayoutManager(this);
        this.recycleViewListaItemServico.setLayoutManager(layoutManager);
        this.recycleViewListaItemServico.setItemAnimator(new DefaultItemAnimator());
        this.recycleViewListaItemServico.addItemDecoration(new SimpleDividerItemDecoration(
                this
        ));

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        this.recycleViewListaItemServico.setLayoutManager(linearLayoutManager);
        /*
        List<ItemServico> itemServicos = ItemServico.getItensServicos(servicoId);
        Log.i("Count:", Integer.toString(itemServicos.size()));
        this.recycleViewListaItemServico.setAdapter(new ItemServicoAdapter(itemServicos,this));
        */
        ImageButton imageButtonSalvar =(ImageButton) findViewById(R.id.imgButtonSalvarServicoPrestado);

        imageButtonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ItemServico> servicosItens = new ArrayList<ItemServico>();

                for (int i = recycleViewListaItemServico.getChildCount() - 1; i >= 0; i--) {
                    final View view = recycleViewListaItemServico.getChildAt(i);
                    RecyclerView.ViewHolder viewHolder = recycleViewListaItemServico.getChildViewHolder(view);
                    if (viewHolder != null && viewHolder instanceof ItemServicoViewHolder) {
                        final ItemServicoViewHolder itemServicoViewHolder = (ItemServicoViewHolder) viewHolder;
                        if(itemServicoViewHolder.chkItemServicoSelecionado.isChecked()){
                            //Toast.makeText(view.getContext(), "Selected Item selecionado: " + itemServicoViewHolder.itemServicoId.getText().toString(), Toast.LENGTH_SHORT).show();
                            servicosItens.add(createItemServicos(itemServicoViewHolder));
                        }
                    }
                }

                ServicoPrestado servicoPrestado = ServicoPrestado.getInstanceParaSalvarServicoPrestado(servicoId);
                servicoPrestado.setServicoValor(limparCaracteresInvalidos(textViewValorTotal.getText().toString()));

                salvarSevicoPrestado(servicoPrestado,servicosItens);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        servicoItemServices = ServicoItemServices.createRecycleViewServicoItem(this,this.recycleViewListaItemServico,this.servicoId);
        servicoItemServices.setServicos();
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        servicoItemServices = ServicoItemServices.createRecycleViewServicoItem(this,this.recycleViewListaItemServico,this.servicoId);
        servicoItemServices.setServicos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_item_servico, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
       // Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.search_item:
                // do your code
                return true;
            case R.id.upload_item:
                // do your code
                return true;
            case R.id.NovoItemServico:
                abrirCadastroItemServicoActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void abrirCadastroItemServicoActivity(){
        Intent intent = new Intent(this, ItemServicoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("ServicoId",this.servicoId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void salvarSevicoPrestado(ServicoPrestado servicoPrestado,List<ItemServico> itemServicos) {
        ServicoPrestadoServices servicoPrestadoServices = ServicoPrestadoServices.createServicoPrestado(this);

        servicoPrestadoServices.Salvar(servicoPrestado,itemServicos);
       // Toast.makeText(ListaItemServicoActivity.this, "Serviço salvo com sucesso", Toast.LENGTH_SHORT).show();
        Snackbar.make(findViewById(R.id.layoutListaItensDoServico), "Serviço salvo com sucesso.", Snackbar.LENGTH_LONG).show();

    }
    private ItemServico createItemServicos(ItemServicoViewHolder itemServicoViewHolder){

        String itemServicoId = itemServicoViewHolder.itemServicoId.getText().toString();
        String itemServicoNome =  itemServicoViewHolder.textNomeItemServico.getText().toString();
        Double itemServicoPreco = limparCaracteresInvalidos(itemServicoViewHolder.textPrecoItemServico.getText().toString());

        ItemServico itemServico = new ItemServico(this.servicoId);
        itemServico.setQuantidadeItemServico(1);
        itemServico.setValorTotalDoItem(itemServicoPreco);
        itemServico.setNomeItemServico(itemServicoNome);
        itemServico.setId(itemServicoId);
        itemServico.setPrecoItemServico(itemServicoPreco);
        return itemServico;
    }
    private Double limparCaracteresInvalidos(String valor){
       // Toast.makeText(ListaItemServicoActivity.this, valor, Toast.LENGTH_SHORT).show();
        Log.i("limparCaracteres",valor);
        return Double.parseDouble(valor.replace(".","").replace(",",".").replace("R$",""));
    }
}
