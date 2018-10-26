package com.xavierluz.servicoonline;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.xavierluz.servicoonline.servico.ServicoItem;
import com.xavierluz.servicoonline.servico.ServicoItemServices;

import java.math.BigDecimal;

public class ItemServicoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editPrecoSevico;
    private EditText editNomeItemServico;
    private EditText editDescricaoItemServico;
    private MascaraMonetaria mascaraMonetaria;
    private FirebaseDatabase database;
    private DatabaseReference refServicos;
    private  String servicoId;
    private ServicoItemServices servicoItemServices;
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
        this.editDescricaoItemServico =(EditText) findViewById(R.id.editDescricaoItemServico);
        //get the intent in the target activity
        Intent intent = getIntent();
        //get the attached bundle from the intent
        Bundle extras = intent.getExtras();
        //Extracting the stored data from the bundle
        servicoId= extras.getString("ServicoId");
        //Toast.makeText(this, "Selected Item: " + servicoId, Toast.LENGTH_SHORT).show();
        imageButtonItemSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
                adicionarServicoItem(servicoId, editNomeItemServico.getText().toString(), editDescricaoItemServico.getText().toString(),limparCaracteresInvalidos(editPrecoSevico.getText().toString()));
                limparCampos();
               // Toast.makeText(ItemServicoActivity.this, "Item do serviço cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                Snackbar.make(findViewById(R.id.activity_item_servico_cadastro), "Item do serviço cadastrado com sucesso.", Snackbar.LENGTH_LONG).show();

            }
        });
        this.mascaraMonetaria = new MascaraMonetaria(editPrecoSevico);
        this.editPrecoSevico.addTextChangedListener(mascaraMonetaria);
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("itemservicos");
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

    private void adicionarServicoItem(String servicoId,String nome, String descricao, Double preco){
        ServicoItem servicoItem = new ServicoItem(servicoId,nome,descricao,preco,"AT");
        //this.refServicos.push().setValue(servicoItem);
        servicoItemServices =  ServicoItemServices.createServicoItem(this,servicoId);
        servicoItemServices.Salvar(servicoItem);
    }
    private void limparCampos(){
        editNomeItemServico.setText("");
        editDescricaoItemServico.setText("");
        editPrecoSevico.setText("");
    }
    private Double limparCaracteresInvalidos(String valor){
        return Double.parseDouble(valor.replace(".","").replace(",","."));
    }
}
