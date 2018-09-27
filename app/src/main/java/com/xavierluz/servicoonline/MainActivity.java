package com.xavierluz.servicoonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    SignInButton signInButton;
    GoogleApiClient mGoogleApiClient;

    private static final String TAG = "SingInActivity";
    private static final int RC_SIGN_IN = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        this.signInButton =(SignInButton) findViewById(R.id.sign_in_button);
        this.signInButton.setOnClickListener(this);*/

       ImageButton abrir =(ImageButton) findViewById(R.id.imageButton);
       abrir.setOnClickListener(onClickAbrir());
    }
    private View.OnClickListener onClickAbrir(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view){
                abrirSingActivity();
            }
        };
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
