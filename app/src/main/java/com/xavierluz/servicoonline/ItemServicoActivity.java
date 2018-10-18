package com.xavierluz.servicoonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.math.BigDecimal;

public class ItemServicoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editPrecoSevico;
    private EditText editNomeItemServico;
    private MascaraMonetaria mascaraMonetaria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_servico);
        toolbar = (Toolbar) findViewById(R.id.toolbarItemServico);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Display icon in the toolbar
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        ImageButton imageButtonItemSalvar = (ImageButton) findViewById(R.id.imgButtonSalvarItemServico);
        this.editPrecoSevico = (EditText) findViewById(R.id.editValorItemServico);
        this.editNomeItemServico = (EditText) findViewById(R.id.editNomeItemServico);

        imageButtonItemSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
            }
        });
        this.mascaraMonetaria = new MascaraMonetaria(editPrecoSevico);
        this.editPrecoSevico.addTextChangedListener(mascaraMonetaria);

    }
    private void validarCampos() {
        Log.i("ValorServico",this.mascaraMonetaria.valorSemMascara().toString());

        if ( this.mascaraMonetaria.valorSemMascara() < 1) {
            editPrecoSevico.setError("Valor do serviço tem que ser maior que zero.");
        }
        if (this.editNomeItemServico.getText().length() == 0) {
            this.editNomeItemServico.setError("Digite o nome do serviço a ser prestado!");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }



}
