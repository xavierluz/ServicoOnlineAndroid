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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xavierluz.servicoonline.servico.Servico;


public class ServicoCadastroActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseDatabase database;
    private DatabaseReference refServicos;
    private final String TAG="ServicoCadastroActivity";
    private EditText editNomeServico;
    private EditText editDescricaoServico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_cadastro);

        toolbar = (Toolbar) findViewById(R.id.toolbarServico);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Display icon in the toolbar
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.database = FirebaseDatabase.getInstance();
        this.refServicos = database.getReference("servicos");
        ImageButton imageButtonSalvar = (ImageButton) findViewById(R.id.imageButtonServicoSalvar);
        this.editNomeServico =(EditText) findViewById(R.id.editNomeDoServico);
        this.editDescricaoServico =(EditText) findViewById(R.id.editDescricaoDoServico);
        imageButtonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarServico(editNomeServico.getText().toString(),editDescricaoServico.getText().toString());
                Toast.makeText(ServicoCadastroActivity.this, "Serviço cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                limparCampos();
            }
        });
        refServicos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getKey();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

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
    private void adicionarServico(String nome, String descricao){
        Servico servico = new Servico(nome,descricao,"AT");
        this.refServicos.push().setValue(servico);
    }
    private void limparCampos(){
        editNomeServico.setText("");
        editDescricaoServico.setText("");
    }
}
