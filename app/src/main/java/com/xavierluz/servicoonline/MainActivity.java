package com.xavierluz.servicoonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private FirebaseAuth mAuth;
    private SignInButton signInButton;
    private GoogleApiClient mGoogleApiClient;
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

        mAuth = FirebaseAuth.getInstance();
        // Configure Google Sign In
        /*
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        this.signInButton =(SignInButton) findViewById(R.id.sign_in_button);
        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                signIn();
                iniciarActivityPrincipal();

            }
        });
        */
        ImageButton imgButtonLogin =(ImageButton) findViewById(R.id.imgButtonLogin);
        imgButtonLogin.setOnClickListener(buttonLogin());

        buttonNovoUsuario = (Button) findViewById(R.id.buttonNovoUsuario);
        buttonNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(MainActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

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
    private View.OnClickListener buttonLogin(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loginUsuario();
            }
        };
    }
    private void loginUsuario(){
        mAuth.signInWithEmailAndPassword(emailUsuario.getText().toString(), senhaUsuario.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            iniciarActivityPrincipal();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Verificar se usuário está cadastrado!",
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
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            iniciarActivityPrincipal();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.activity_main_inicio), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    private void handleSignInResult(GoogleSignInResult googleSignInResult) {
        Log.d(TAG,"handleSignInResult :" + googleSignInResult.isSuccess());

        if(googleSignInResult.isSuccess()){
            GoogleSignInAccount signInAccount = googleSignInResult.getSignInAccount();
            firebaseAuthWithGoogle(signInAccount);

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

    public void signOut(){
        if(mGoogleApiClient !=null) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {

                    Toast.makeText(MainActivity.this, "Saída realizado com sucesso!", Toast.LENGTH_SHORT);
                }
            });
        }
    }
}
