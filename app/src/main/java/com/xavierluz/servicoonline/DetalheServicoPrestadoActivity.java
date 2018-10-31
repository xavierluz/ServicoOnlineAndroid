package com.xavierluz.servicoonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xavierluz.servicoonline.prestados.ServicoPrestadoServices;

public class DetalheServicoPrestadoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private android.support.v7.widget.RecyclerView recycleViewListaItemServico;
    private RecyclerView.LayoutManager layoutManager;
    private String servicoId;
    private String servicoPrestadoId;
    private RecyclerView recycleViewPrestadoDetalhe;
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
        Toast.makeText(this, "Selected Item: " + servicoPrestadoId, Toast.LENGTH_SHORT).show();
        View view =(View) findViewById(R.id.activity_item_servico_prestado_detalhe);

        ServicoPrestadoServices servicoPrestadoServices = ServicoPrestadoServices.createRecycleViewServicoPrestado(DetalheServicoPrestadoActivity.this, this.recycleViewPrestadoDetalhe);
        servicoPrestadoServices.setServicoPrestado(view, this.servicoPrestadoId);
        servicoPrestadoServices.setServicosPrestadoDetalhe(this.servicoPrestadoId);
    }
}
