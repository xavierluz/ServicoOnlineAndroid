package com.xavierluz.servicoonline;

import android.content.Intent;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.xavierluz.servicoonline.bibliotecas.ValidarEmail;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private EditText nomeUsuario;
    private EditText emailUsuario;
    private EditText senhaUsuario;
    private EditText confirmaSenhaUsuario;
    private FirebaseAuth mAuth;
    private ImageButton imageButtonSalvar;
    private final String TAG="CadastroUsuario";
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        toolbar = (Toolbar) findViewById(R.id.toolbarCastroUsuario);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Display icon in the toolbar
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.nomeUsuario = (EditText) findViewById(R.id.editNomeUsuario);
        this.emailUsuario = (EditText) findViewById(R.id.editEmailUsuario);
        this.senhaUsuario = (EditText) findViewById(R.id.editSenhaUsuario);
        this.confirmaSenhaUsuario = (EditText) findViewById(R.id.editConfirmaSenhaUsuario);
        this.imageButtonSalvar = (ImageButton) findViewById(R.id.imgButtonSalvarCadastroUsuario);
        mAuth = FirebaseAuth.getInstance();

        imageButtonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCamposUsuario()) {
                    criarUsuario(emailUsuario.getText().toString(), senhaUsuario.getText().toString());
                    criarDisplayName(nomeUsuario.getText().toString());
                    loginUsuario(emailUsuario.getText().toString(), senhaUsuario.getText().toString());
                    iniciarActivityPrincipal();
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private void iniciarActivityPrincipal(){
        Intent intent =  new Intent(CadastroUsuarioActivity.this, ServicosActivity.class);
        startActivity(intent);
    }
    private void criarUsuario(String email, String senha) {
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastroUsuarioActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    private void loginUsuario(String email, String senha){
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(CadastroUsuarioActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
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
    private void criarDisplayName(String nome) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nome)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }
    private boolean validarCamposUsuario(){
        boolean retorno=true;
        if (this.nomeUsuario.getText().length() == 0) {
            this.nomeUsuario.setError("Digite o nome do serviço a ser prestado!");
            retorno=false;
        }
        if (this.emailUsuario.getText().length() == 0) {
            this.emailUsuario.setError("Digite o email par o usuário");
            retorno=false;
        }else if (!ValidarEmail.isEmailValid(this.emailUsuario.getText().toString())) {
            this.emailUsuario.setError("Email digitado é ínvalido");
            retorno=false;
        }
        if (this.senhaUsuario.getText().length() == 0) {
            this.senhaUsuario.setError("Por favor digite a senha");
            retorno=false;
        }else{
            if (this.confirmaSenhaUsuario.getText().length() == 0) {
                this.confirmaSenhaUsuario.setError("Por favor digite a confirmação da senha");
                retorno=false;
            }else {
                String _senhaUsuario = this.senhaUsuario.getText().toString();
                String _senhaConfirma = this.confirmaSenhaUsuario.getText().toString();
                if (!_senhaUsuario.equals(_senhaConfirma)) {
                    this.senhaUsuario.setError("Senha não confere, por favor verificar!");
                    retorno=false;
                }
            }
        }

        return retorno;
    }
}
