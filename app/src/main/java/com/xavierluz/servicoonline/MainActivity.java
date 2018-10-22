package com.xavierluz.servicoonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private FirebaseAuth mAuth;
    SignInButton signInButton;
    GoogleApiClient mGoogleApiClient;
    private EditText emailUsuario;
    private EditText senhaUsuario;
    private static final String TAG = "SingInActivity";
    private static final int RC_SIGN_IN = 9001;
    private Button buttonNovoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        this.emailUsuario = (EditText) findViewById(R.id.editEmailLogin);
        this.senhaUsuario = (EditText) findViewById(R.id.editSenhaLogin);
       /* GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        this.signInButton =(SignInButton) findViewById(R.id.sign_in_button);
        this.signInButton.setOnClickListener(this);*/

        ImageButton imgButtonLogin =(ImageButton) findViewById(R.id.imgButtonLogin);
        imgButtonLogin.setOnClickListener(buttonLogin(emailUsuario.getText().toString(), senhaUsuario.getText().toString()));

        buttonNovoUsuario = (Button) findViewById(R.id.buttonNovoUsuario);
        buttonNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(MainActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser !=null){
            iniciarActivityPrincipal();
        }
        //Toast.makeText(this,currentUser.getDisplayName() ,Toast.LENGTH_SHORT);

    }
    private void iniciarActivityPrincipal(){
        Intent intent =  new Intent(MainActivity.this, ServicosActivity.class);
        startActivity(intent);
    }
    private View.OnClickListener buttonLogin(final String email, final String senha){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loginUsuario(email,senha);
            }
        };
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
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    private void abrirSingActivity(){
        Intent intent = new Intent(this, ServicosActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                abrirSingActivity();
                break;

        }
    }

    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient);
        startActivityForResult(signInIntent, this.RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == this.RC_SIGN_IN){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(googleSignInResult);
        }
    }

    private void handleSignInResult(GoogleSignInResult googleSignInResult) {
        Log.d(TAG,"handleSignInResult :" + googleSignInResult.isSuccess());

        if(googleSignInResult.isSuccess()){
            GoogleSignInAccount signInAccount = googleSignInResult.getSignInAccount();
            Toast.makeText(this,signInAccount.getDisplayName() ,Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG,"onConnectionFailed: " + connectionResult);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void signOut(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
    }
}
