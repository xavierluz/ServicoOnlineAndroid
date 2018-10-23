package com.xavierluz.servicoonline;

import android.content.Intent;
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

import java.util.List;

public class ListaItemServicoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recycleViewListaItemServico;
    private RecyclerView.LayoutManager layoutManager;
    private String servicoId;
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
        Toast.makeText(this, "Selected Item: " + servicoId, Toast.LENGTH_SHORT).show();

        TextView textViewValorTotal = (TextView) findViewById(R.id.textViewValorTotalServicoPrestado);
        textViewValorTotal.setText("0.00");
        this.recycleViewListaItemServico = (RecyclerView) findViewById(R.id.recycleViewListaItemServico);
        this.recycleViewListaItemServico.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        this.recycleViewListaItemServico.setLayoutManager(layoutManager);
        this.recycleViewListaItemServico.setItemAnimator(new DefaultItemAnimator());
        this.recycleViewListaItemServico.addItemDecoration(new SimpleDividerItemDecoration(
                this
        ));

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        this.recycleViewListaItemServico.setLayoutManager(linearLayoutManager);

        List<ItemServico> itemServicos = ItemServico.getItensServicos(servicoId);
        Log.i("Count:", Integer.toString(itemServicos.size()));
        this.recycleViewListaItemServico.setAdapter(new ItemServicoAdapter(itemServicos,this));

        ImageButton imageButtonSalvar =(ImageButton) findViewById(R.id.imgButtonSalvarServicoPrestado);

        imageButtonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = recycleViewListaItemServico.getChildCount() - 1; i >= 0; i--) {
                    final View view = recycleViewListaItemServico.getChildAt(i);
                    RecyclerView.ViewHolder viewHolder = recycleViewListaItemServico.getChildViewHolder(view);
                    if (viewHolder != null && viewHolder instanceof ItemServicoViewHolder) {
                            final ItemServicoViewHolder itemServicoViewHolder = (ItemServicoViewHolder) viewHolder;
                        if(itemServicoViewHolder.chkItemServicoSelecionado.isChecked()){
                            Toast.makeText(view.getContext(), "Selected Item: " + itemServicoViewHolder.itemServicoId, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
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
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
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
        bundle.putInt("ServicoId",1);
        intent.putExtra("Servico",bundle);
        startActivity(intent);
    }

    private void salvarSevicoPrestado(List<ItemServicoViewHolder> itemServicos){

    }
}
